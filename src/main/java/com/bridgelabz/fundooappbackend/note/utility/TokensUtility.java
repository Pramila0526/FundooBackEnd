package com.bridgelabz.fundooappbackend.note.utility;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.bridgelabz.fundooappbackend.note.message.Messages;
import com.bridgelabz.fundooappbackend.user.exception.custom.TokenException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
/**********************************************************************************************************
 * @author :Pramila Tawari 
 * Purpose :Class For Generating the Token
 *
 *********************************************************************************************************/

@Component
public class TokensUtility 
{
	private final String SECRET_KEY = "secret"; // itz not a good pratice to hardcode in the program.inReal scenario
												// write this in application.properties file.

	public String createToken(String email)
	{
		return Jwts.builder().setSubject(email).setIssuedAt(new Date(System.currentTimeMillis()))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}

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
