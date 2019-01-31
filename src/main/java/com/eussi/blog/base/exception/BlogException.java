package com.eussi.blog.base.exception;

/**
 * 
 * @author wangxm
 *
 */
public class BlogException extends RuntimeException {
	private static final long serialVersionUID = -7443213283905815106L;
	private int code;

	public BlogException() {
	}

	/**
	 * BlogException
	 * @param code 错误代码
	 */
	public BlogException(int code) {
		super("code=" + code);
		this.code = code;
	}

	/**
	 * BlogException
	 * @param message 错误消息
	 */
	public BlogException(String message) {
		super(message);
	}

	/**
	 * BlogException
	 * @param cause 捕获的异常
	 */
	public BlogException(Throwable cause) {
		super(cause);
	}

	/**
	 * BlogException
	 * @param message 错误消息
	 * @param cause 捕获的异常
	 */
	public BlogException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * BlogException
	 * @param code 错误代码
	 * @param message 错误消息
	 */
	public BlogException(int code, String message) {
		super(message);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
