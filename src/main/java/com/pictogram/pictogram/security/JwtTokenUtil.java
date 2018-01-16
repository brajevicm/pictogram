package com.pictogram.pictogram.security;

import com.pictogram.pictogram.commons.utils.TimeProvider;
import com.pictogram.pictogram.security.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Project: pictogram
 * Date: 12-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@Component
public class JwtTokenUtil implements Serializable {

  private static final long serialVersionUID = 246986342520092170L;

  static final String CLAIM_KEY_USERNAME = "sub";
  static final String CLAIM_KEY_AUDIENCE = "aud";
  static final String CLAIM_KEY_CREATED = "iat";

  static final String AUDIENECE_UNKNOWN = "unknown";
  static final String AUDIENCE_WEB = "web";
  static final String AUDIENCE_MOBILE = "mobile";
  static final String AUDIENCE_TABLET = "tablet";

  @Autowired
  private TimeProvider timeProvider;

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expiration}")
  private Long expiration;

  public String getUsernameFromToken(String token) {
    return getClaimFromToken(token, Claims::getSubject);
  }

  private Date getIssuedAtDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getIssuedAt);
  }

  private Date getExpirationDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }

  private String getAudienceFromToken(String token) {
    return getClaimFromToken(token, Claims::getAudience);
  }

  private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFomToken(token);

    return claimsResolver.apply(claims);
  }

  private Claims getAllClaimsFomToken(String token) {
    return Jwts.parser()
      .setSigningKey(secret)
      .parseClaimsJws(token)
      .getBody();
  }

  private Boolean isTokenExpired(String token) {
    final Date expirationDate = getExpirationDateFromToken(token);

    return expirationDate.before(timeProvider.now());
  }

  private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
    return (lastPasswordReset != null && created.before(lastPasswordReset));
  }

  private String generateAudience(Device device) {
    String audience = AUDIENECE_UNKNOWN;

    if (device.isNormal()) {
      audience = AUDIENCE_WEB;
    } else if (device.isMobile()) {
      audience = AUDIENCE_MOBILE;
    } else if (device.isTablet()) {
      audience = AUDIENCE_TABLET;
    }

    return audience;
  }

  private Boolean ignoreTokenExpiration(String token) {
    String audience = getAudienceFromToken(token);

    return (AUDIENCE_TABLET.equals(audience) || AUDIENCE_MOBILE.equals(audience));
  }

  public String generateToken(UserDetails userDetails, Device device) {
    Map<String, Object> claims = new HashMap<>();

    return doGenerateToken(claims, userDetails.getUsername(), generateAudience(device));
  }

  private String doGenerateToken(Map<String, Object> claims, String subject, String audience) {
    final Date createdDate = timeProvider.now();
    final Date expirationDate = calculateExpirationDate(createdDate);

    return Jwts.builder()
      .setClaims(claims)
      .setSubject(subject)
      .setAudience(audience)
      .setIssuedAt(createdDate)
      .setExpiration(expirationDate)
      .signWith(SignatureAlgorithm.HS512, secret)
      .compact();
  }

  public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
    final Date created = getIssuedAtDateFromToken(token);

    return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
      && (!isTokenExpired(token) || ignoreTokenExpiration(token));
  }

  public Boolean validateToken(String token, UserDetails userDetails) {
    JwtUser user = (JwtUser) userDetails;
    final String username = getUsernameFromToken(token);
    final Date created = getIssuedAtDateFromToken(token);

    return (
      username.equals(user.getUsername())
        && !isTokenExpired(token)
        && !isCreatedBeforeLastPasswordReset(created, user.getLastPasswordResetDate())
    );
  }

  public String refreshToken(String token) {
    final Date createdDate = timeProvider.now();
    final Date expirationDate = calculateExpirationDate(createdDate);

    final Claims claims = getAllClaimsFomToken(token);
    claims.setIssuedAt(createdDate);
    claims.setExpiration(expirationDate);

    return Jwts.builder()
      .setClaims(claims)
      .signWith(SignatureAlgorithm.HS512, secret)
      .compact();
  }

  private Date calculateExpirationDate(Date createdDate) {
    return new Date(createdDate.getTime() + expiration * 1000);
  }
}
