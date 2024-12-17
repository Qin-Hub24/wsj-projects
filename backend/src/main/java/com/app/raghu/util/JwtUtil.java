package com.app.raghu.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	@Value("${app.secret}")
	private String secret;  // 用于加密和解密 JWT 的密钥

	// 7. Validate Token: 校验 token 的有效性，检查用户名和 token 是否匹配且未过期
	public boolean validateToken(String token, String username) {
		String usernameInToken = getUsername(token); // 从 token 中提取用户名
		return (usernameInToken.equals(username) && !isTokenExpired(token)); // 校验用户名和过期时间
	}

	// 6. Check Current and Exp Date: 检查 token 是否过期
	private boolean isTokenExpired(String token) {
		final Date expiration = getExpDate(token); // 获取 token 的过期时间
		return expiration.before(new Date()); // 判断是否过期
	}

	// 5. Generate Token with Empty Claims: 生成一个带有空的 Claims 的 token
	public String generateToken(String username) {
		Map<String, Object> claims = new HashMap<>(); // 创建空的 Claims
		return generateToken(claims, username); // 调用带 Claims 的方法生成 token
	}

	// 4. Read Username from Token: 从 token 中提取用户名
	public String getUsername(String token) {
		return getClaims(token).getSubject(); // 返回 token 中的 subject，通常是用户名
	}

	// 3. Read Expiration Date: 从 token 中提取过期时间
	public Date getExpDate(String token) {
		return getClaims(token).getExpiration(); // 返回 token 中的过期时间
	}

	// 2. Read Claims: 获取 token 中的所有 Claims
	private Claims getClaims(String token) {
		return Jwts.parser()
				.setSigningKey(secret) // 使用 secret 密钥解析
				.parseClaimsJws(token) // 解析 token
				.getBody(); // 返回 Claims 的 body 部分
	}

	// 1. Generate Token: 根据 claims 和 subject 生成一个新的 token
	private String generateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder()
				.setClaims(claims) // 设置 Claims
				.setSubject(subject) // 设置 subject（用户名）
				.setIssuer("RAGHU") // 设置 issuer，标识 token 的发放者
				.setIssuedAt(new Date(System.currentTimeMillis())) // 设置 issuedAt，表示 token 创建时间
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(30))) // 设置过期时间，30 分钟后过期
				.signWith(SignatureAlgorithm.HS512, secret) // 使用 HS512 算法和 secret 密钥签名
				.compact(); // 生成 token
	}


}
