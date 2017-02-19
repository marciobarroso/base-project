package com.icodeuplay.base.common.utils;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class EnvironmentUtils {

	public interface LibC extends Library {

		public int setenv(String name, String value, int overwrite);

		public int unsetenv(String name);

		static LibC libc = (LibC) Native.loadLibrary("c", LibC.class);
	}

	public interface WinLibC extends Library {
		public int _putenv(String name);
	}

	public interface LinuxLibC extends Library {

		public int setenv(String name, String value, int overwrite);

		public int unsetenv(String name);

	}

	static public class POSIX {

		static Object libc;
		static {
			if (System.getProperty("os.name").equals("Linux")) {
				libc = Native.loadLibrary("c", LinuxLibC.class);
			} else {
				libc = Native.loadLibrary("msvcrt", WinLibC.class);
			}
		}

		public int setenv(String name, String value, int overwrite) {
			if (libc instanceof LinuxLibC) {
				return ((LinuxLibC) libc).setenv(name, value, overwrite);
			} else {
				return ((WinLibC) libc)._putenv(name + "=" + value);
			}
		}

		public int unsetenv(String name) {
			if (libc instanceof LinuxLibC) {
				return ((LinuxLibC) libc).unsetenv(name);
			} else {
				return ((WinLibC) libc)._putenv(name + "=");
			}
		}
	}

	static POSIX libc = new POSIX();

	public static void setEnvironmentVariable(String variable, String value) {
		new POSIX().setenv(variable, value, 0);
	}

	public static String getEnvironmentVariable(String variable) {
		return System.getenv(variable);
	}
}
