package com.nirvana.fdfs;

import java.io.IOException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

public class FastDFSContext {

	private static StorageClient1 client = null;

	private static TrackerServer trackerServer = null;

	public static StorageClient1 getClient() {
		return client;
	}

	public static void closeTrackerServer() throws IOException {
		trackerServer.close();
	}

	static {
		try {
			ClientGlobal.init("nirvana.properties");
			TrackerClient tracker = new TrackerClient();
			trackerServer = tracker.getConnection();
			client = new StorageClient1(trackerServer, null);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

	}

}
