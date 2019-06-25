/*
 * Copyright 2013-2018 Lilinfeng.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package Nova.ch2.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

//在没有tcp的keepalive配置的情况下，非正常关闭客户端socket，服务端是没有感知的，
//服务端的socket的列表一直会存在
//测试的时候，在client处于timeunit.sleep情况下，关闭客户端。这个时候，服务端是没有反应的


/**
 * @author lilinfeng
 * @date 2014年2月14日
 * @version 1.0
 */
public class TimeClientShutDownwithoutexpect {

    /**
     * @param args
     */
    public static void main(String[] args) {

	int port = 8080;
	if (args != null && args.length > 0) {

	    try {
		port = Integer.valueOf(args[0]);
	    } catch (NumberFormatException e) {
		// 采用默认值
	    }

	}
	Socket socket = null;
	BufferedReader in = null;
	PrintWriter out = null;
	try {
	    socket = new Socket("127.0.0.1", port);
	    in = new BufferedReader(new InputStreamReader(
		    socket.getInputStream()));
	    out = new PrintWriter(socket.getOutputStream(), true);
	    out.println("QUERY TIME ORDER");
	    System.out.println("Send order 2 server succeed.");
	    String resp = in.readLine();
	    System.out.println("Now is : " + resp);
	    try {
			TimeUnit.SECONDS.sleep(3000000);
		}catch (InterruptedException ex)
		{
			ex.printStackTrace();
		}
		System.out.println("Finish Now" );
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    if (out != null) {
		out.close();
		out = null;
	    }

	    if (in != null) {
		try {
		    in.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		in = null;
	    }

	    if (socket != null) {
		try {
		    socket.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		socket = null;
	    }
	}
    }
}
