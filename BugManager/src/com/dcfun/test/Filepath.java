package com.dcfun.test;

import java.io.File;
import java.util.ArrayList;

public class Filepath{
	private static ArrayList filelist = new ArrayList();
   
    public static void main(String[] args) {
        refreshFileList("e:\\crash_infos");
    }
    public static void refreshFileList(String strPath) {
        File dir = new File(strPath);
        File[] files = dir.listFiles();

        for (int i = 0; i < files.length; i++) {
        	String strFileName = files[i].getAbsolutePath().toLowerCase();
        	System.out.println(strFileName);
        	filelist.add(files[i].getAbsolutePath());                   
        }
    }
}