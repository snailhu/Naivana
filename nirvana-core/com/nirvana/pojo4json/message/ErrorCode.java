package com.nirvana.pojo4json.message;

/** �����롣 */
public enum ErrorCode {

	NOT_LOGIN(12, "�û�δ��½"),

	ILLEGAL_ARGUMENT(2, "�Ƿ�����"),

	USER_EXIST(3, "�û��Ѵ���"),

	DATA_NOT_FOUND(4, "�Ҳ�����¼"),

	ACCESS_DENIED(5, "Ȩ�޲���"),

	SERVER_ERROR(6, "�������ڲ�����"),

	ERROR_STATE(7, "״̬����"),

	ALLREADY_EXISTS(8, "�����ظ�"),

	NOT_FOUND(9, "�Ҳ�������"),

	AUTHENTICATE_FAILURE(10, "��֤ʧ��"),

	LOGIN_EXPIRED(11, "��½����"),

	LOGIN_INVALID(12, "��½ʧЧ"),

	NOT_EMPTY(13, "");

	private final int code;

	private final String text;

	private ErrorCode(int code, String text) {
		this.text = text;
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public int getCode() {
		return code;
	}
}
