package br.una.laboratorio.util;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

public class ContentBody {
	
	public static <T> T entity( HttpServletRequest request, Class<T> classOfT ){
		String body =  body(request);
		return new Gson().fromJson(body, classOfT);
	}
	
	private static String body( HttpServletRequest request ){
		StringBuffer body = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				body.append(line);
			return body.toString();
		} catch (Exception e) { 
			return null;
		}
	}
	
}
