package com.eussi.blog.base.utils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by wangxueming on 2019/1/30.
 */
public class CommonUtils {
    /**
     * 拼接in or not in 查询字符串
     * @param field
     * @param collection
     * @param type 0|1 in|not in
     * @return
     */
    public static String concatInQuery(String field, Collection collection, int type) {
        if (null != collection && !collection.isEmpty()) {
            StringBuilder sb = new StringBuilder(" " + field);
            if(type==0) {
                sb.append(" in (");
            }
            else {
                sb.append(" not in (");
            }

            for(Object obj : collection) {
                sb.append(obj + ",");
            }
            return sb.toString().
                    substring(0, sb.toString().length() - 1).
                    concat(") ");
        } else {
            //返回null造成查询条件没有了，结果是查询出所有数据
            if(type==0) {
                return field.concat(" in ('')");
            }
            else {
                return field.concat(" not in ('')");
            }
        }
//        return null;
    }

    public static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "localhost";
        }
    }

    public static String ipToIPv4Str(byte[] ip) {
        return ip.length != 4?null:(ip[0] & 255) + "." + (ip[1] & 255) + "." + (ip[2] & 255) + "." + (ip[3] & 255);
    }

    public static byte[] getIP() {
        try {
            Enumeration e = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            byte[] internalIP = null;

            while(e.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface)e.nextElement();
                Enumeration addresses = netInterface.getInetAddresses();

                while(addresses.hasMoreElements()) {
                    ip = (InetAddress)addresses.nextElement();
                    if(ip != null && ip instanceof Inet4Address) {
                        byte[] ipByte = ip.getAddress();
                        if(ipByte.length == 4 && ipCheck(ipByte)) {
                            if(!isInternalIP(ipByte)) {
                                return ipByte;
                            }

                            if(internalIP == null) {
                                internalIP = ipByte;
                            }
                        }
                    }
                }
            }

            if(internalIP != null) {
                return internalIP;
            } else {
                throw new RuntimeException("Can not get local ip");
            }
        } catch (Exception var6) {
            throw new RuntimeException("Can not get local ip", var6);
        }
    }

    private static boolean ipCheck(byte[] ip) {
        if(ip.length != 4) {
            throw new RuntimeException("illegal ipv4 bytes");
        } else {
            return ip[0] >= 1 && ip[0] <= 126?(ip[1] == 1 && ip[2] == 1 && ip[3] == 1?false:ip[1] != 0 || ip[2] != 0 || ip[3] != 0):(ip[0] >= -128 && ip[0] <= -65?(ip[2] == 1 && ip[3] == 1?false:ip[2] != 0 || ip[3] != 0):(ip[0] >= -64 && ip[0] <= -33?(ip[3] == 1?false:ip[3] != 0):false));
        }
    }

    public static boolean isInternalIP(byte[] ip) {
        if(ip.length != 4) {
            throw new RuntimeException("illegal ipv4 bytes");
        } else {
            return ip[0] == 127;
        }
    }
}
