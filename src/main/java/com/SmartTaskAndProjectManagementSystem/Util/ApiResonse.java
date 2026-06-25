package com.SmartTaskAndProjectManagementSystem.Util;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResonse<T> {

	private boolean success;
	private String message;
	private T data;

	private LocalDateTime timestamp = LocalDateTime.now();

	public static <T> ApiResonse<T> success(String message, T data) {

		return ApiResonse.<T>builder().success(true).message(message).data(data).timestamp(LocalDateTime.now()).build();

	}

	public static <T> ApiResonse<T> error(String message) {
		return ApiResonse.<T>builder().success(false).message(message).timestamp(LocalDateTime.now()).build();
	}

}
