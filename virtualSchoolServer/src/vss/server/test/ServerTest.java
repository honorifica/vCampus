package vss.server.test;

import vss.common.*;
import vss.server.dao.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.*;

import vss.server.bz.*;

public class ServerTest {
	public static void main(String[] args) throws IOException {
		System.out.println("Server strated");
		ServerSocketSrvImp sssi=new ServerSocketSrvImp();
		sssi.run();
	}
}