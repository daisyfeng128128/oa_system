package com.baofeng.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 功能：自定义日期方法库
 * 
 * @author renlr@outlook.com
 * */
public class CustomDateUtils {

	private static CustomDateUtils instance;

	public CustomDateUtils() {
	}

	public static CustomDateUtils getInsance() {
		if (instance == null)
			instance = new CustomDateUtils();
		return instance;
	}

	public abstract class AbsDateUtils {

		// 把完整时间分解成日期和时间两部分
		// function DateOf(const AValue: TDateTime): TDateTime;
		// function TimeOf(const AValue: TDateTime): TDateTime;

		// 判断是否有效时间
		// function IsInLeapYear(const AValue: TDateTime): Boolean;
		// function IsPM(const AValue: TDateTime): Boolean;
		// function IsValidDate(const AYear, AMonth, ADay: Word): Boolean;
		// function IsValidTime(const AHour, AMinute, ASecond, AMilliSecond:
		// Word):
		// Boolean;
		// function IsValidDateTime(const AYear, AMonth, ADay, AHour, AMinute,
		// ASecond, AMilliSecond: Word): Boolean;
		// function IsValidDateDay(const AYear, ADayOfYear: Word): Boolean;
		// function IsValidDateWeek(const AYear, AWeekOfYear, ADayOfWeek: Word):
		// Boolean;
		// function IsValidDateMonthWeek(const AYear, AMonth, AWeekOfMonth,
		// ADayOfWeek: Word): Boolean;
		//
		// 年月包含的天数和星期数
		// function WeeksInYear(const AValue: TDateTime): Word; {ISO 8601}
		// function WeeksInAYear(const AYear: Word): Word; {ISO 8601}
		// function DaysInYear(const AValue: TDateTime): Word;
		// function DaysInAYear(const AYear: Word): Word;
		// function DaysInMonth(const AValue: TDateTime): Word;
		// function DaysInAMonth(const AYear, AMonth: Word): Word;

		// function Today: TDateTime;
		// function Yesterday: TDateTime;
		// function Tomorrow: TDateTime;
		// function IsToday(const AValue: TDateTime): Boolean;
		// function IsSameDay(const AValue, ABasis: TDateTime): Boolean;

		// 得到当前时间的年、第几个月、第几个星期、天
		// function YearOf(const AValue: TDateTime): Word;
		// function MonthOf(const AValue: TDateTime): Word;
		// function WeekOf(const AValue: TDateTime): Word;
		// {WeekOf函数会得到该星期为一年的第几个星期}
		// function DayOf(const AValue: TDateTime): Word;
		// function HourOf(const AValue: TDateTime): Word;
		// function MinuteOf(const AValue: TDateTime): Word;
		// function SecondOf(const AValue: TDateTime): Word;
		// function MilliSecondOf(const AValue: TDateTime): Word;

		// 年月日星期的起止时间
		// function StartOfTheYear(const AValue: TDateTime): TDateTime;
		// function EndOfTheYear(const AValue: TDateTime): TDateTime;
		// function StartOfAYear(const AYear: Word): TDateTime;
		// function EndOfAYear(const AYear: Word): TDateTime;
		//
		// function StartOfTheMonth(const AValue: TDateTime): TDateTime;
		// function EndOfTheMonth(const AValue: TDateTime): TDateTime;
		// function StartOfAMonth(const AYear, AMonth: Word): TDateTime;
		// function EndOfAMonth(const AYear, AMonth: Word): TDateTime;

		// function StartOfTheWeek(const AValue: TDateTime): TDateTime; {ISO
		// 8601}
		// function EndOfTheWeek(const AValue: TDateTime): TDateTime; {ISO 8601}
		// function StartOfAWeek(const AYear, AWeekOfYear: Word; const
		// ADayOfWeek:
		// Word = 1): TDateTime;
		// function EndOfAWeek(const AYear, AWeekOfYear: Word; const ADayOfWeek:
		// Word = 7): TDateTime;
		//
		// function StartOfTheDay(const AValue: TDateTime): TDateTime;
		// function EndOfTheDay(const AValue: TDateTime): TDateTime;
		// function StartOfADay(const AYear, AMonth, ADay: Word): TDateTime;
		// overload;
		// function EndOfADay(const AYear, AMonth, ADay: Word): TDateTime;
		// overload;
		// function StartOfADay(const AYear, ADayOfYear: Word): TDateTime;
		// overload;
		// function EndOfADay(const AYear, ADayOfYear: Word): TDateTime;
		// overload;
		//
		// { 时间顺序函数 }
		// function MonthOfTheYear(const AValue: TDateTime): Word;
		// function WeekOfTheYear(const AValue: TDateTime): Word; overload; {ISO
		// 8601}
		// function WeekOfTheYear(const AValue: TDateTime; var AYear: Word):
		// Word;
		// overload;
		// function DayOfTheYear(const AValue: TDateTime): Word;
		// function HourOfTheYear(const AValue: TDateTime): Word;
		// function MinuteOfTheYear(const AValue: TDateTime): LongWord;
		// function SecondOfTheYear(const AValue: TDateTime): LongWord;
		// function MilliSecondOfTheYear(const AValue: TDateTime): Int64;

		// function WeekOfTheMonth(const AValue: TDateTime): Word; overload;
		// {ISO
		// 8601x}
		// function WeekOfTheMonth(const AValue: TDateTime; var AYear, AMonth:
		// Word): Word; overload;
		// function DayOfTheMonth(const AValue: TDateTime): Word; // 一个月的第几天
		// function HourOfTheMonth(const AValue: TDateTime): Word;
		// function MinuteOfTheMonth(const AValue: TDateTime): Word;
		// function SecondOfTheMonth(const AValue: TDateTime): LongWord;
		// function MilliSecondOfTheMonth(const AValue: TDateTime): LongWord;

		// 返回星期几，1表示星期一，而7表示星期日。 星期一是一周的第一天，ISO 8601
		// function DayOfTheWeek(const AValue: TDateTime): Word; {ISO 8601}
		// function HourOfTheWeek(const AValue: TDateTime): Word; {ISO 8601}
		// function MinuteOfTheWeek(const AValue: TDateTime): Word; {ISO 8601}
		// function SecondOfTheWeek(const AValue: TDateTime): LongWord; {ISO
		// 8601}
		// function MilliSecondOfTheWeek(const AValue: TDateTime): LongWord;
		// {ISO
		// 8601}
		//
		// function HourOfTheDay(const AValue: TDateTime): Word;
		// function MinuteOfTheDay(const AValue: TDateTime): Word;
		// function SecondOfTheDay(const AValue: TDateTime): LongWord;
		// function MilliSecondOfTheDay(const AValue: TDateTime): LongWord;
		// function MinuteOfTheHour(const AValue: TDateTime): Word;
		// function SecondOfTheHour(const AValue: TDateTime): Word;
		// function MilliSecondOfTheHour(const AValue: TDateTime): LongWord;
		// function SecondOfTheMinute(const AValue: TDateTime): Word;
		// function MilliSecondOfTheMinute(const AValue: TDateTime): LongWord;
		// function MilliSecondOfTheSecond(const AValue: TDateTime): Word;

		// { For a given date these functions tell you the which day of the week
		// of
		// the
		// month (or year). If its a Thursday, they will tell you if its the
		// first,
		// second, etc Thursday of the month (or year). Remember, even though
		// its
		// the first Thursday of the year it doesn't mean its the first week of
		// the
		// year. See ISO 8601 above for more information. }
		// function NthDayOfWeek(const AValue: TDateTime): Word;

		// { 范围检查函数 }
		// function WithinPastYears(const ANow, AThen: TDateTime; const AYears:
		// Integer): Boolean;
		// function WithinPastMonths(const ANow, AThen: TDateTime; const
		// AMonths:
		// Integer): Boolean;
		// function WithinPastWeeks(const ANow, AThen: TDateTime; const AWeeks:
		// Integer): Boolean;
		// function WithinPastDays(const ANow, AThen: TDateTime; const ADays:
		// Integer): Boolean;
		// function WithinPastHours(const ANow, AThen: TDateTime; const AHours:
		// Int64): Boolean;
		// function WithinPastMinutes(const ANow, AThen: TDateTime; const
		// AMinutes:
		// Int64): Boolean;
		// function WithinPastSeconds(const ANow, AThen: TDateTime; const
		// ASeconds:
		// Int64): Boolean;
		// function WithinPastMilliSeconds(const ANow, AThen: TDateTime; const
		// AMilliSeconds: Int64): Boolean;
		//
		// { Range query functions }
		// { 范围查询函数 }
		// function YearsBetween(const ANow, AThen: TDateTime): Integer;
		// function MonthsBetween(const ANow, AThen: TDateTime): Integer;
		// function WeeksBetween(const ANow, AThen: TDateTime): Integer;
		// function DaysBetween(const ANow, AThen: TDateTime): Integer;
		// function HoursBetween(const ANow, AThen: TDateTime): Int64;
		// function MinutesBetween(const ANow, AThen: TDateTime): Int64;
		// function SecondsBetween(const ANow, AThen: TDateTime): Int64;
		// function MilliSecondsBetween(const ANow, AThen: TDateTime): Int64;

		// { Range spanning functions }
		// { 范围跨度函数 }
		// function YearSpan(const ANow, AThen: TDateTime): Double;
		// function MonthSpan(const ANow, AThen: TDateTime): Double;
		// function WeekSpan(const ANow, AThen: TDateTime): Double;
		// function DaySpan(const ANow, AThen: TDateTime): Double;
		// function HourSpan(const ANow, AThen: TDateTime): Double;
		// function MinuteSpan(const ANow, AThen: TDateTime): Double;
		// function SecondSpan(const ANow, AThen: TDateTime): Double;
		// function MilliSecondSpan(const ANow, AThen: TDateTime): Double;

		// { Increment/decrement datetime fields }
		// { 时间增减函数}
		// function IncYear(const AValue: TDateTime; const ANumberOfYears:
		// Integer =
		// 1): TDateTime;
		// // function IncMonth is in SysUtils
		// function IncWeek(const AValue: TDateTime; const ANumberOfWeeks:
		// Integer =
		// 1): TDateTime;
		// function IncDay(const AValue: TDateTime; const ANumberOfDays: Integer
		// =
		// 1): TDateTime;
		// function IncHour(const AValue: TDateTime; const ANumberOfHours: Int64
		// =
		// 1): TDateTime;
		// function IncMinute(const AValue: TDateTime; const ANumberOfMinutes:
		// Int64
		// = 1): TDateTime;
		// function IncSecond(const AValue: TDateTime; const ANumberOfSeconds:
		// Int64
		// = 1): TDateTime;
		// function IncMilliSecond(const AValue: TDateTime; const
		// ANumberOfMilliSeconds: Int64 = 1): TDateTime;

		// { 时间组成函数}
		// function EncodeDateTime(const AYear, AMonth, ADay, AHour, AMinute,
		// ASecond, AMilliSecond: Word): TDateTime;
		// procedure DecodeDateTime(const AValue: TDateTime; out AYear, AMonth,
		// ADay, AHour, AMinute, ASecond, AMilliSecond: Word);
		// function EncodeDateWeek(const AYear, AWeekOfYear: Word; const
		// ADayOfWeek:
		// Word = 1): TDateTime;
		// procedure DecodeDateWeek(const AValue: TDateTime; out AYear,
		// AWeekOfYear,
		// ADayOfWeek: Word);
		// function EncodeDateDay(const AYear, ADayOfYear: Word): TDateTime;
		// procedure DecodeDateDay(const AValue: TDateTime; out AYear,
		// ADayOfYear:
		// Word);
		// function EncodeDateMonthWeek(const AYear, AMonth, AWeekOfMonth,
		// ADayOfWeek: Word): TDateTime;
		// procedure DecodeDateMonthWeek(const AValue: TDateTime; out AYear,
		// AMonth,
		// AWeekOfMonth, ADayOfWeek: Word);
		// function TryEncodeDateTime(const AYear, AMonth, ADay, AHour, AMinute,
		// ASecond, AMilliSecond: Word; out AValue: TDateTime): Boolean;
		// function TryEncodeDateWeek(const AYear, AWeekOfYear: Word; out
		// AValue:
		// TDateTime; const ADayOfWeek: Word = 1): Boolean;
		// function TryEncodeDateDay(const AYear, ADayOfYear: Word; out AValue:
		// TDateTime): Boolean;
		// function TryEncodeDateMonthWeek(const AYear, AMonth, AWeekOfMonth,
		// ADayOfWeek: Word; var AValue: TDateTime): Boolean;
		// function TryRecodeDateTime(const AValue: TDateTime; const AYear,
		// AMonth,
		// ADay, AHour, AMinute, ASecond, AMilliSecond: Word; out AResult:
		// TDateTime): Boolean;
		// procedure DecodeDayOfWeekInMonth(const AValue: TDateTime; out AYear,
		// AMonth, ANthDayOfWeek, ADayOfWeek: Word);
		// function EncodeDayOfWeekInMonth(const AYear, AMonth, ANthDayOfWeek,
		// ADayOfWeek: Word): TDateTime;
		// function TryEncodeDayOfWeekInMonth(const AYear, AMonth,
		// ANthDayOfWeek,
		// ADayOfWeek: Word; out AValue: TDateTime): Boolean;

		// { Recode functions for datetime fields }
		// { 重编码时间函数}
		// function RecodeYear(const AValue: TDateTime; const AYear: Word):
		// TDateTime;
		// function RecodeMonth(const AValue: TDateTime; const AMonth: Word):
		// TDateTime;
		// function RecodeDay(const AValue: TDateTime; const ADay: Word):
		// TDateTime;
		// function RecodeHour(const AValue: TDateTime; const AHour: Word):
		// TDateTime;
		// function RecodeMinute(const AValue: TDateTime; const AMinute: Word):
		// TDateTime;
		// function RecodeSecond(const AValue: TDateTime; const ASecond: Word):
		// TDateTime;
		// function RecodeMilliSecond(const AValue: TDateTime; const
		// AMilliSecond:
		// Word): TDateTime;
		// function RecodeDate(const AValue: TDateTime; const AYear, AMonth,
		// ADay:
		// Word): TDateTime;
		// function RecodeTime(const AValue: TDateTime; const AHour, AMinute,
		// ASecond, AMilliSecond: Word): TDateTime;
		// function RecodeDateTime(const AValue: TDateTime; const AYear, AMonth,
		// ADay, AHour, AMinute, ASecond, AMilliSecond: Word): TDateTime;

		// { Fuzzy comparison }
		// { 不准确的比较}
		// function CompareDateTime(const A, B: TDateTime): TValueRelationship;
		// function SameDateTime(const A, B: TDateTime): Boolean;
		// function CompareDate(const A, B: TDateTime): TValueRelationship;
		// function SameDate(const A, B: TDateTime): Boolean;
		// function CompareTime(const A, B: TDateTime): TValueRelationship;
		// function SameTime(const A, B: TDateTime): Boolean;

		// { 错误报告 }
		// procedure InvalidDateTimeError(const AYear, AMonth, ADay, AHour,
		// AMinute,
		// ASecond, AMilliSecond: Word; const ABaseDate: TDateTime = 0);
		// procedure InvalidDateWeekError(const AYear, AWeekOfYear, ADayOfWeek:
		// Word);
		// procedure InvalidDateDayError(const AYear, ADayOfYear: Word);
		// procedure InvalidDateMonthWeekError(const AYear, AMonth,
		// AWeekOfMonth,
		// ADayOfWeek: Word);
		// procedure InvalidDayOfWeekInMonthError(const AYear, AMonth,
		// ANthDayOfWeek, ADayOfWeek: Word);
		//
		// { Julian and Modified Julian Date conversion support }
		// { Be aware that not all Julian Dates (or MJD) are encodable as a
		// TDateTime }
		// function DateTimeToJulianDate(const AValue: TDateTime): Double;
		// function JulianDateToDateTime(const AValue: Double): TDateTime;
		// function TryJulianDateToDateTime(const AValue: Double; out ADateTime:
		// TDateTime): Boolean;
		// function DateTimeToModifiedJulianDate(const AValue: TDateTime):
		// Double;
		// function ModifiedJulianDateToDateTime(const AValue: Double):
		// TDateTime;
		// function TryModifiedJulianDateToDateTime(const AValue: Double; out
		// ADateTime: TDateTime): Boolean;
		//
		// { Unix 转换 }
		// function DateTimeToUnix(const AValue: TDateTime): Int64;
		// function UnixToDateTime(const AValue: Int64): TDateTime;

		// CompareDateTime 比较两个日期时间值的大小
		// CompareTime 比较两个日期时间值时间部分的大小
		// DateOf 去除日期时间值的时间部分
		// DateTimeToJulianDate 转换日期时间值为儒略日
		// DateTimeToModifiedJulianDate 转换日期时间值为改进的儒略日
		// DateTimeToUnix 转换日期时间值为Unix/Linus日期时间值
		// Day of week 常量 ISO 8601标准中一周各天顺序的 常量
		// DayOf 返回一个日期时间值的天
		// DayOfTheMonth 返回一个日期时间值的天
		// DayOfTheWeek 返回一个日期时间值是那星期的第几天
		// DayOfTheYear 返回一个日期时间值是那年的第多少天
		// DaysBetween 返回两个日期时间值之间相差的整数天数
		// DaysInAMonth 返回指定年、月的天数
		// DaysInAYear 返回指定年的天数
		// DaysInMonth 返回一个日期时间值的那个月的天数
		// DaysInYear 返回一个日期时间值的那一年的天数
		// DaySpan 返回两个日期时间值之间相差的小数天数
		// DecodeDateDay 返回一个日期时间值的年份和是一年的第多少天
		// DecodeDateMonthWeek 返回一个日期时间值的年、月、那个月的第几周、那周的第几天
		// DecodeDateTime 返回一个日期时间值的年、月、日、时、分、秒、毫秒
		// DecodeDateWeek 返回一个日期时间值的年、一年的第多少周、一周的第几天
		// DecodeDayOfWeekInMonth 返回一个日期时间值的年、月、一周的第几天、那个月的第几个星期几
		// EncodeDateDay 返回指定年和一年的第多少天的日期时间值
		// EncodeDateMonthWeek 返回指定年、月、那个月的第几周、那周的第几天的日期时间值
		// EncodeDateTime 返回指定年、月、日、时、分、秒，毫秒返的日期时间值
		// EncodeDateWeek 返回指定年、那年的第多少周、那周的第几天的日期时间值
		// EncodeDayOfWeekInMonth 返回指定年、月、那个月的第几个星期几的日期时间值
		// EndOfADay 返回指定年、那年第多少天的最后一秒的日期时间值
		// EndOfAMonth 返回指定年、月的最后一天最后一秒的日期时间值
		// EndOfAWeek 返回指定年、那年第多少周、那周第几天的最后一秒的日期时间值
		// EndOfAYear 返回指定年的最后一天最后一秒的日期时间值
		// EndOfTheDay 返回指定日期时间值的那一天最后一秒的日期时间值
		// EndOfTheMonth 返回指定日期时间值的那个月的最后一天最后一秒的日期时间值
		// EndOfTheWeek 返回指定日期时间值的那一周的最后一天最后一秒的日期时间值
		// EndOfTheYear 返回指定日期时间值的那一年最后一天最后一秒的日期时间值
		// HourOf 返回指定日期时间值的小时部分
		// HourOfTheDay 返回指定日期时间值的小时部分.
		// HourOfTheMonth 返回从指定日期时间值的那个月的第一天0点到指定日期的小时已经度过的小时数
		// HourOfTheWeek 返回从指定日期时间值中那一周第一天0点到指定日期的那个小时 已经度过的小时数
		// HourOfTheYear 返回从指定日期时间值中 那一年第一天0点到指定日期的那个小时已经度过的小时数
		// HoursBetween 返回两个指定日期时间值之间相差的小时数
		// HourSpan 返回两个指定日期时间值之间相差的小时数(包括小数部分)
		// IncDay 返回日期时间值向后推移指定天数后的值
		// IncHour 返回日期时间值向后推移指定小时数的值
		// IncMilliSecond 返回日期时间值向后推移指定毫秒数的值
		// IncMinute 返回日期时间值向后推移指定分钟数的值
		// IncSecond 返回日期时间值向后推移指定秒数的值
		// IncWeek 返回日期时间值向后推移指定星期数的值
		// IncYear 返回日期时间值向后推移指定星期数的值
		// IsInLeapYear 判断指定的日期时间值的年份是否为闰年
		// IsPM 判断指定的日期时间值的时间是否是中午12:0:0之后
		// IsSameDay 判断一个日期时间值与标准日期时间值是否是同一天
		// IsToday 判断一个日期时间值是否是当天
		// IsValidDate 判断指定的年、月、日是否是有效的日期
		// IsValidDateDay 判断指定的年、该年的天数是否是该年有效的天数
		// IsValidDateMonthWeek 判断指定的年、月、该月的第几周、该周的第几天是否是有效的日期
		// IsValidDateTime 判断指定的年、月、日、时、分、秒、毫秒是否是有效的日期时间值
		// IsValidDateWeek 判断指定的年、该年的第多少周、该周第几天是否是有效的日期
		// IsValidTime 判断指定的时、分、秒、毫秒是否是有效的时间
		// JulianDateToDateTime 转换儒略日期为日期时间值
		// MilliSecondOf 返回指定日期时间值的毫秒部分
		// MilliSecondOfTheDay 返回指定日期时间值的那天0时0分0秒0毫秒开始到其指定时间的毫秒数
		// MilliSecondOfTheHour 返回指定日期时间值的那一小时0分0秒0毫秒开始到其指定时间的毫秒数
		// MilliSecondOfTheMinute 返回指定日期时间值的那一分钟0秒0毫秒开始到其指定时间的毫秒数
		// MilliSecondOfTheMonth 返回指定日期时间值的那个月1日分钟0秒0毫秒开始到其指定时间的毫秒数
		// MilliSecondOfTheSecond 返回指定日期时间值的毫秒部分
		// MilliSecondOfTheWeek 返回指定日期时间值的那周星期一0时0分0秒0毫秒到其指定时间的毫秒数
		// MilliSecondOfTheYear 返回指定日期时间值的那年1月1日0时0分0秒0毫秒到其指定时间的毫秒数
		// MilliSecondsBetween 返回两个指定日期时间值之间相差的毫秒数(整数)
		// MilliSecondSpan 返回两个指定日期时间值 之间相差的毫秒数(小数)
		// MinuteOf 返回指定日期时间值 分钟部分
		// MinuteOfTheDay 返回指定日期时间值的那天0时0分开始到其指定时间的分钟数
		// MinuteOfTheHour 返回指定日期时间值的分钟部分
		// MinuteOfTheMonth 返回指定日期时间值的那个月1日0时0分开始到其指定时间的分钟数
		// MinuteOfTheWeek 返回指定日期时间值的那周第1天0时0分开始到其指定时间的分钟数
		// MinuteOfTheYear 返回指定日期时间值的那年1月1日0时0分开始到其指定时间的分钟数
		// MinutesBetween 返回两个指定日期时间值之间相差的分钟数(整数)
		// MinuteSpan 返回两个指定日期时间值之间相差的分钟数(包含小数)
		// ModifiedJulianDateToDateTime 转换修正的儒略日为日期时间值
		// MonthOf 返回指定日期时间值的月份部分
		// MonthOfTheYear 返回指定日期时间值的月份部分
		// MonthsBetween 返回两个指定日期时间值之间相差的月份(整数)
		// MonthSpan 返回两个指定日期时间值之间相差的月份(包含小数)
		// NthDayOfWeek 返回指定日期时间值该月的第几个星期几
		// OneHour 常量 Delphi与时间成反比的常量
		// OneMillisecond 常量 Delphi与时间成反比的常量
		// OneMinute 常量 Delphi与时间成反比的常量
		// OneSecond 常量 Delphi与时间成反比的常量
		// RecodeDate 替换指定日期时间值的日期部分
		// RecodeDateTime 选择替换指定日期时间值
		// RecodeDay 替换指定日期时间值 的日部分
		// RecodeHour 替换指定日期时间值 的小时部分
		// RecodeMilliSecond 替换指定日期时间值的毫秒部分
		// RecodeMinute 替换指定日期时间值的分钟部分
		// RecodeMonth 替换指定日期时间值的月份部分
		// RecodeSecond 替换指定日期时间值的秒部分
		// RecodeTime 替换指定日期时间值的时间部分
		// RecodeYear 替换指定日期时间值的年份部分
		// SameDate 判断两个日期时间值的年、月、日部分是否相同
		// SameDateTime 判断两个日期时间值的年、月、日、时、分、秒、毫秒是否相同
		// SameTime 判断两个日期时间值的时、分、秒、毫秒部分是否相同
		// SecondOf 返回指定日期时间值的秒部分
		// SecondOfTheDay 返回从指定日期时间值那天0时0分0秒到其指定时间的秒数
		// SecondOfTheHour 返回从指定日期时间值那小时0分0秒到其指定时间的秒数
		// SecondOfTheMinute 返回从指定日期时间值那分钟0秒到其指定时间的秒数
		// SecondOfTheMonth 返回从指定日期时间值那个月1日0时0分0秒到其指定时间的秒数
		// SecondOfTheWeek 返回从指定日期时间值所在周的星期一0时0分0秒到其指定时间的秒数
		// SecondOfTheYear 返回从指定日期时间值那年的1月1日0时0分0秒到其指定时间的秒数
		// SecondsBetween 返回两个指定日期时间值之间相差的秒数(整数)
		// SecondSpan 返回两个指定日期时间值之间相差的秒数(包含小数)
		// StartOfADay 返回指定那天开始(0时0分0秒0毫秒)的日期时间值
		// StartOfAMonth 返回指定年、月的第一天开始(0时0分0秒0毫秒)的日期时间值
		// StartOfAWeek 返回指定年、第多少周、第几天开始(0时0分0秒0毫秒)的日期时间值
		// StartOfAYear 返回指定年开始(1月1日0时0分0秒0毫秒)的日期时间值
		// StartOfTheDay 返回指定日期时间值那天开始(0时0分0秒0毫秒)的日期时间值
		// StartOfTheMonth 返回指定日期时间值那个月开始(1日0时0分0秒0毫秒)的日期时间值
		// StartOfTheWeek 返回指定日期时间值那周开始(第一天0时0分0秒0毫秒)的日期时间值
		// StartOfTheYear 返回指定日期时间值那年开始(1月1日0时0分0秒0毫秒)的日期时间值
		// TimeOf 返回指定日期时间值的时间部分
		// Today 返回当天的日期
		// Tomorrow 返回下一天的日期
		// TryEncodeDateDay 计算指定年、该年第多少天的日期时间值
		// TryEncodeDateMonthWeek 计算指定年、月、该月第几周、该周第几天的日期时间值
		// TryEncodeDateTime 转换指定年、月、日、时、分、秒、毫秒为日期时间值
		// TryEncodeDateWeek 转换指定年、该第多少周、该周第几天为日期时间值
		// TryEncodeDayOfWeekInMonth 转换指定年、月、该月第几个星期几为日期时间值
		// TryJulianDateToDateTime 转换指定儒略日为日期时间值
		// TryModifiedJulianDateToDateTime 转换指定修正儒略日为日期时间值
		// TryRecodeDateTime 选择替换指定日期时间值的某些部分
		// UnixToDateTime 转换Unix或Linux日期、时间值为Delphi日期时间值
		// WeekOf 返回指定日期时间值是某年的第多少周
		// WeekOfTheMonth 返回指定日期时间值是某月的第 几周
		// WeekOfTheYear 返回指定日期时间值是某年的第多少周
		// WeeksBetween 返回两个指定日期时间值 之间相差多少周(整数)
		// WeeksInAYear 返回指定的年有多少周
		// WeeksInYear 返回指定日期时间值的那年有多少周
		// WeekSpan 返回两个指定日期时间值之间相差多少周(包含小数)
		// WithinPastDays 判断两个日期之间相差 是否在指定天数的范围内
		// WithinPastHours 判断两个日期 时间值之间相差是否在指定小时的范围内
		// WithinPastMilliSeconds 判断两个日期时间值之间相差是否在指定毫秒的范围内
		// WithinPastMinutes 判断两个日期时间值之间相差是否在指定分钟的范围内
		// WithinPastMonths 判断两个日期时间值之间相差是否在指定月份的范围内
		// WithinPastSeconds 判断两个日期时间值之间相差是否在指定秒数的范围内
		// WithinPastWeeks 判断两个日期时间值之间相差是否在指定星期数的范围内
		// WithinPastYears 判断两个日期时间值之间相差是否在指定年数的范围内
		// YearOf 返回指定日期时间值中年份部分
		// YearsBetween 返回两个指定日期时间值之间相差的年份数(整数)
		// YearSpan 返回两个指定日期时间值之间相差的年份数(包含小数)
		// Yesterday 返回当前日期之前一天(昨天)的日期
	}

	public static final String format1 = "yyyy-MM-dd";
	public static final String format2 = "yyyy-MM-dd HH:mm:ss";
	public static final String format3 = "M月dd";
	public static final String format4 = "yyyy.MM.dd";
	public static final String format5 = "yyyyMMdd";
	public static final String format6 = "M月dd日 HH:mm";
	public static final String format7 = "yyyy-MM";
	public static final String format8 = "yyyy-MM-dd HH:mm";
	public static final String format9 = "yyyy年MM月";
	public static final String format10 = "yyyy年MM月dd日";
	public static final String format11 = "yyyy年MM月dd日 HH:mm:ss";

	/** 功能：日期转字符串 */
	public static String format(Date date, String format) {
		String value = null;
		if (date != null && format != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			value = dateFormat.format(date);
		}
		return value;
	}

	/** 功能：字符串转日期 */
	public static Date format(String date, String format) {
		Date value = null;
		if (date != null && format != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			try {
				value = dateFormat.parse(date);
			} catch (Exception e) {
			}
		}
		return value;
	}

	public static class Handler {

		/** 功能：返回指定年、月的天数 */
		public static int daysInMonth(final int year, final int month) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, year);
			calendar.set(Calendar.MONTH, month - 1);
			calendar.set(Calendar.DATE, 1);
			calendar.roll(Calendar.DATE, -1);
			return calendar.get(Calendar.DATE);
		}

		/** 功能：返回指定年、月的天数,date:YYYY-MM */
		public static int daysInMonth(String date) {
			if (date != null && date.trim().length() > 0) {
				String[] $date = date.split("-");
				if ($date != null && $date.length == 2) {
					Calendar calendar = Calendar.getInstance();
					calendar.set(Calendar.YEAR, Integer.valueOf($date[0]));
					calendar.set(Calendar.MONTH, Integer.valueOf($date[1]) - 1);
					calendar.set(Calendar.DATE, 1);
					calendar.roll(Calendar.DATE, -1);
					return calendar.get(Calendar.DATE);
				}
			}
			return 0;
		}

		/** 功能：返回当月最后一天时间 date:YYYY-MM-dd HH:mm:ss */
		public static Date currentMonthFirstDay() {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			return calendar.getTime();
		}

		/** 功能：返回当月第一天时间 date:YYYY-MM-dd HH:mm:ss */
		public static Date currentMonthEndDay() {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.add(Calendar.MONTH, 1);
			calendar.add(Calendar.DATE, -1);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			return calendar.getTime();
		}

		/** 功能：返回当月第一天时间 date:YYYY-MM-dd HH:mm:ss */
		public static Date currentMonthFirstDay(Date date) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			return calendar.getTime();
		}

		/** 功能：返回当月最后一天时间 date:YYYY-MM-dd HH:mm:ss */
		public static Date currentMonthEndDay(Date date) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.add(Calendar.MONTH, 1);
			calendar.add(Calendar.DATE, -1);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			return calendar.getTime();
		}

		/** 功能：返回上月第一天时间 date:YYYY-MM-dd HH:mm:ss */
		public static Date lastMonthFirstDay() {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -1);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			return calendar.getTime();
		}

		/** 功能：返回上月最后一天时间 date:YYYY-MM-dd HH:mm:ss */
		public static Date lastMonthEndDay() {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.add(Calendar.DATE, -1);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			return calendar.getTime();
		}

		/** 功能：返回上月第一天时间 date:YYYY-MM-dd HH:mm:ss */
		public static Date lastMonthFirstDay(Date date) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, -1);
			calendar.set(Calendar.DATE, 1);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			return calendar.getTime();
		}

		/** 功能：返回上月最后一天时间 date:YYYY-MM-dd HH:mm:ss */
		public static Date lastMonthEndDay(Date date) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.add(Calendar.DATE, -1);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			return calendar.getTime();
		}

		/** 功能：返回指定时间一天最小 */
		public static Date currentBeginDay(Date date) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			return calendar.getTime();
		}

		/** 功能：返回指定时间一天最大 */
		public static Date currentEndDay(Date date) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			return calendar.getTime();
		}
	}

	public static void main(String[] args) {
		Date date = CustomDateUtils.Handler.currentMonthFirstDay();
		System.out.println(CustomDateUtils.format(date, CustomDateUtils.format2));
	}
}
