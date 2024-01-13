package day17;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class IpEx1 {

	public static void main(String[] args) {
		InetAddress address = null;
		
		try {
			// localhost means inner IP. every PCs are same
			address = InetAddress.getByName("localhost");
			System.out.println(address+"\n");
			
			
			// look up one of the IPs that www.naver.com uses
			address = InetAddress.getByName("www.naver.com");
			System.out.println(address+"\n");
			
			// look up every IP that www.naver.com uses
			InetAddress [] addressList = InetAddress.getAllByName("www.naver.com");
			Arrays.stream(addressList).forEach(addr->System.out.println(addr));
			
		} catch (UnknownHostException e) {
			System.out.println("cannot find address that has identical name.");
			e.printStackTrace();
		}
				

	}

}
