package com.cesfam.presmo.backend.apirest.auth;

public class JwtConfig {

	public static final String LLAVE_SECRETA = "alguna.clave.secreta.12345678";
	public static final String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\r\n" + 
			"MIIEowIBAAKCAQEAydVfavfHlhg4pQiTtudLphoCJkLQzMDrPa04pPq5TsB0Yfd3\r\n" + 
			"xkGF2Vcu28jlywuusUVCV9dUUOGy79J+0bl9aUHAngZ9SsTMc117xOVJjgjmZqhE\r\n" + 
			"VoqLZOj4urW3YorUMnQx6WtEIohUT1iCyJAjaEbP9wQPIrqAFHWjx6qVnQTcNe1V\r\n" + 
			"5iiu9Vra71SLiTYvPo5rM69EUlXERo/0NUidSwv18Y/N4WVcNFeQ5PtZ8BJW04cJ\r\n" + 
			"BCCb+dHsVroaRTt7nmHKKobcnwNOkxpM2Lmjqj/+hvMFRpFJ8Fn29lKTsqX9fqWi\r\n" + 
			"BeBShA9p+P6mCbg11ObmjfHrjzvE1unIRti9CwIDAQABAoIBAEHUDPX3cKo8piT1\r\n" + 
			"gjASijLXosHV4wIVHeSU3be5ar0LuinFq9QGxOAZNlhK0uABpG1++F9sBL0Ij/L2\r\n" + 
			"RZSjmgq9flRMK4s6WrB52gdi/TWNI5Bx6zAKqeI0tgM3h2NlgkYebbUC3kshCdaD\r\n" + 
			"OMyvbkzxY5zePw2FpIaHw+44bemy+si/6mcW1T0MVLF8MTypoCTut8pgH5Amc5Pr\r\n" + 
			"xDytpE2d/GiJ9MVJwc+Yg3IeFJbjLSGS89b677xMQ0lB7f7RONu4EUwW4aBMVmjE\r\n" + 
			"Z0oVOqFbTZTBATP5KrL6L1tLasLJrOqTn4ugSLx8B45bDqkrPNbzEyhkbSSJePVQ\r\n" + 
			"f8X72WECgYEA6SPJNvlZLMHHA24DWXlehOMZjtIFh8AvSWBL8cYB58HjWnTxIt3A\r\n" + 
			"iis4nb437q8juAflNbUI+gB6R9xAXC2UPQxKwJcAxhb8WWsk3+slKb1JcHu3so5U\r\n" + 
			"afcXOI4dJYrFN8SyhBj62Szr3uySTT/LV9YNK+1bftfszpBoyzAAjpECgYEA3Z++\r\n" + 
			"f1lBhaDSQUjlkKq2ryc3VyDuNSXq40JZhIHdh+C6ZfgR/8H+ZG73UZdlpUfbflFf\r\n" + 
			"uLjArIjYmYBp0pZgDCA6sQsViQaMap8UjVGmewU9kJ4/4wCMUdVkZOBNBgdYJZJ3\r\n" + 
			"41tVsMJIzUlaVXThbe2a7zLyXNj0kZEQkzvz19sCgYA7S1q1nJ1NgMs42BYJ/5BG\r\n" + 
			"g+vQIyI88GgWZWNrTkzVwL4uF2fs0jFR8sQnemWHUwvmljt5eV2bF9wBwsarqvv0\r\n" + 
			"uteYO896kgYER/fM62U8RGQVM6H4y7I0cR96xsoq7ixSjrhIWIIUEhS7Ubez5fG1\r\n" + 
			"mT8oWOnYTNpxftDoPIEnwQKBgQCBmG754wSokmFAodLz5I/NHn6C6ZPB6nlPCTZ1\r\n" + 
			"gMc9NJFrHUPRLeyHaEghIwniO4HDfOwfhZHNknwuTuQSq7JghNwtOREPvo+PrZSE\r\n" + 
			"CtHyMB9PGOq84E4tcD4zquH/e331ApAul/Mk2Idf29wCCsXj1UR7SWxmFMv270Al\r\n" + 
			"CLLPbwKBgAOGgYzMlvOx0SJdH7inmfUBznCkrSvS4V2aHmKFYVdRa4PH+IYMc/8/\r\n" + 
			"qYk/TbyWvPK/eoN8tYo2lCFg3tuaE151HwUzdl44t7UHFVGk9ulK2SoKL4x5+Uva\r\n" + 
			"5Oi6IZZO34g+WLCD0qb532+y/6w4a1Ei03r7aDvkvjD8InfRGroz\r\n" + 
			"-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLIC = "-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAydVfavfHlhg4pQiTtudL\r\n" + 
			"phoCJkLQzMDrPa04pPq5TsB0Yfd3xkGF2Vcu28jlywuusUVCV9dUUOGy79J+0bl9\r\n" + 
			"aUHAngZ9SsTMc117xOVJjgjmZqhEVoqLZOj4urW3YorUMnQx6WtEIohUT1iCyJAj\r\n" + 
			"aEbP9wQPIrqAFHWjx6qVnQTcNe1V5iiu9Vra71SLiTYvPo5rM69EUlXERo/0NUid\r\n" + 
			"Swv18Y/N4WVcNFeQ5PtZ8BJW04cJBCCb+dHsVroaRTt7nmHKKobcnwNOkxpM2Lmj\r\n" + 
			"qj/+hvMFRpFJ8Fn29lKTsqX9fqWiBeBShA9p+P6mCbg11ObmjfHrjzvE1unIRti9\r\n" + 
			"CwIDAQAB\r\n" + 
			"-----END PUBLIC KEY-----";
}
