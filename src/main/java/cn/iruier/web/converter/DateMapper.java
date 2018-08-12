package cn.iruier.web.converter;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;

//重写ObjectMapper
//@Component //等价于<bean>
public class DateMapper extends ObjectMapper{

	public DateMapper() {
		setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	}
}
