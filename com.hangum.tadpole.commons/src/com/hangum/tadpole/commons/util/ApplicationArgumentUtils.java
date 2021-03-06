/*******************************************************************************
 * Copyright (c) 2013 hangum.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     hangum - initial API and implementation
 ******************************************************************************/
package com.hangum.tadpole.commons.util;

import org.apache.log4j.Logger;

import org.eclipse.core.runtime.Platform;

/**
 * 시스템 시작 유틸 
 * 
 * @author hangum
 *
 */
public class ApplicationArgumentUtils {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ApplicationArgumentUtils.class);
	
	/**
	 * 시스템이 개발환경인지?
	 * 
	 * 올챙이 실행환경이 mac 1.7이상, 그외 1.6 이상이다 보니, 
	 * 맥 환경 개발자들이 1.6 환경을 맞추면 시스템 개발환경을 설정하기어려워 -dev가 들어 있는 환경에서는 System checker가 동작하지 않도록 하기위해 사용합니다.
	 * ps) 일괄적으로 1.7로 올리면 좋겠다...
	 * 
	 * @return
	 */
	public static boolean envDev() {
		return checkString("-envDev");
	}
	
	/**
	 * 시스템의 테이블 초기 데이터를 초기화 루틴을 탈것인지 검사합니다.
	 * @return
	 * @throws Exception
	 */
	public static boolean isForceSystemInitialize() {
		return checkString("-forceSystemInitialize");
	}
	
	/**
	 * 엔진이 외부 디비를 사용 할 것인지?
	 * @return
	 * @throws Exception
	 */
	public static boolean isDBServer() {
		return checkString("-dbServer");
	}
	
	/**
	 * <pre>
	 * 	엔진이 디비를 공유 디비정보를 가져온다.
	 * </pre>
	 * 
	 * @return
	 */
	public static String getDbServer() throws Exception {
		return getValue("-dbServer");
	}
	
	/**
	 * 
	 * @return
	 */
	public static boolean isDBPath() {
		return checkString("-dbPath");
	}
	
	/**
	 * 엔진디비를 로컬디비를 사용했을때 path를 적어 줍니다.
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getDBPath() throws Exception {
		return getValue("-dbPath");
	}
	
	/**
	 * 사용자 디비를 사용합니까?
	 * @return
	 */
	public static boolean isUseDB() {
		return checkString("-useDB");
	}
	
	/**
	 * 사용자 디비 목록을 가져옵니다.
	 * @return
	 * @throws Exception
	 */
	public static String getUseDB() throws Exception {
		return getValue("-useDB");
	}
	
	public static boolean isDefaultDB() {
		return checkString("-defaultDB");
	}
	public static String getDefaultDB() throws Exception {
		return getValue("-defaultDB");
	}

	/**
	 * <pre>
	 * application 시작 모드
	 * 
	 * application argement에 -standalone가 있으면 standalone 모드
	 * <pre>
	 * 
	 * @return
	 */
	public static boolean isStandaloneMode() {
		return checkString("-standalone");
	}
	
	/**
	 * test 모드이면
	 * 
	 * @return
	 */
	public static boolean isTestMode() {
		return checkString("-test");
	}
	
	/**
	 * check debug mode
	 */
	public static boolean isDebugMode() {
		return checkString("-debuglog");
	}
	
	/**
	 * runtime시에 argument의 value를 리턴합니다.
	 * 
	 * @param key
	 * @return
	 */
	private static String getValue(String key) throws Exception {
		String[] applicationArgs = Platform.getApplicationArgs();
		for(int i=0; i<applicationArgs.length; i++) {
			String arg = applicationArgs[i];
			if( arg.startsWith(key) ) {
				return applicationArgs[i+1];
			}
		}
	
		throw new Exception("Can't find argument. Find key is " + key);
	}
	
	/**
	 * runtime시에 argument가 있는지 검사합니다.
	 * 
	 * @param checkString
	 * @return
	 */
	private static boolean checkString(String checkString) {
		String[] applicationArgs = Platform.getApplicationArgs();
		
		for (String string : applicationArgs) {
			if( string.startsWith(checkString) ) return true;
		}
		
		return false;
	}
}
