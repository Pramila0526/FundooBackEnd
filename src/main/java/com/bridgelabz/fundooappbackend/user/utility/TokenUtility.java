package com.bridgelabz.fundooappbackend.user.utility;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.bridgelabz.fundooappbackend.user.exception.custom.TokenException;
import com.bridgelabz.fundooappbackend.user.message.Messages;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**********************************************************************************************************
 * @author :Pramila Tawari 
 * Purpose :Class For Generating the Token
 *
 *********************************************************************************************************/

@Component
public class TokenUtility 
{
	private final String SECRET_KEY = "secret"; 

	// Method to create the Token
	public String createToken(String email)
	{
		return Jwts.builder().setSubject(email).setIssuedAt(new Date(System.currentTimeMillis()))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}

	// Method to get User Token
	public String getUserToken(String token) 
	{
		Claims claim = null;
		try 
		{
			System.out.println("token :-" + token);
			claim = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
			System.out.println(claim.getSubject());
		} 
		catch (Exception e)
		{
			throw new TokenException(Messages.INVALID_TOKEN);
		}
		return claim.getSubject();
	}
}
