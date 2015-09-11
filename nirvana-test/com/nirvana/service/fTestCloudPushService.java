package com.nirvana.service;

import com.nirvana.service.impl.CloudPushServiceImpl;

public class fTestCloudPushService {

	private static CloudPushServiceImpl cloudPushServiceImpl = new CloudPushServiceImpl();

	public static void main(String[] args) {
		push();
	}

	public static void push() {
		cloudPushServiceImpl.broadCast("asdfasdfasdf");
	}

}
