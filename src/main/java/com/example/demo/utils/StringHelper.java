//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.utils;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class StringHelper {
    public static final String EMPTY_STRING = "";
    public static final char DOT = '.';
    public static final char UNDERSCORE = '_';
    public static final String COMMA_SPACE = ", ";
    public static final String COMMA = ",";
    public static final String OPEN_PAREN = "(";
    public static final String CLOSE_PAREN = ")";
    public static final char SINGLE_QUOTE = '\'';
    public static final String CRLF = "\r\n";
    public static final int FIANL_TWELVE = 12;
    public static final int HEX_80 = 128;
    public static final int HEX_FF = 255;

    private StringHelper() {
    }

    public static String join(String seperator, String[] strings) {
        int length = strings.length;
        if (length == 0) {
            return "";
        } else {
            StringBuffer buf = (new StringBuffer(length * strings[0].length())).append(strings[0]);

            for(int i = 1; i < length; ++i) {
                buf.append(seperator).append(strings[i]);
            }

            return buf.toString();
        }
    }

    public static String join(String seperator, Iterator objects) {
        StringBuffer buf = new StringBuffer();
        if (objects.hasNext()) {
            buf.append(objects.next());
        }

        while(objects.hasNext()) {
            buf.append(seperator).append(objects.next());
        }

        return buf.toString();
    }

    public static String[] add(String[] x, String seperator, String[] y) {
        String[] result = new String[x.length];

        for(int i = 0; i < x.length; ++i) {
            result[i] = x[i] + seperator + y[i];
        }

        return result;
    }

    public static String repeat(String string, int times) {
        StringBuffer buf = new StringBuffer(string.length() * times);

        for(int i = 0; i < times; ++i) {
            buf.append(string);
        }

        return buf.toString();
    }

    public static String replace(String source, String old, String replace) {
        StringBuffer output = new StringBuffer();
        int sourceLen = source.length();
        int oldLen = old.length();

        int posStart;
        int pos;
        for(posStart = 0; (pos = source.indexOf(old, posStart)) >= 0; posStart = pos + oldLen) {
            output.append(source.substring(posStart, pos));
            output.append(replace);
        }

        if (posStart < sourceLen) {
            output.append(source.substring(posStart));
        }

        return output.toString();
    }

    public static String replace(String template, String placeholder, String replacement, boolean wholeWords) {
        int loc = template.indexOf(placeholder);
        if (loc < 0) {
            return template;
        } else {
            boolean actuallyReplace = wholeWords || loc + placeholder.length() == template.length() || !Character.isJavaIdentifierPart(template.charAt(loc + placeholder.length()));
            String actualReplacement = actuallyReplace ? replacement : placeholder;
            return template.substring(0, loc) + actualReplacement + replace(template.substring(loc + placeholder.length()), placeholder, replacement, wholeWords);
        }
    }

    public static String replaceOnce(String template, String placeholder, String replacement) {
        int loc = template.indexOf(placeholder);
        return loc < 0 ? template : template.substring(0, loc) + replacement + template.substring(loc + placeholder.length());
    }

    public static String[] split(String list, String seperators) {
        return split(list, seperators, false);
    }

    public static String[] split(String list, String seperators, boolean include) {
        StringTokenizer tokens = new StringTokenizer(list, seperators, include);
        String[] result = new String[tokens.countTokens()];

        for(int i = 0; tokens.hasMoreTokens(); result[i++] = tokens.nextToken()) {
        }

        return result;
    }

    public static String unqualify(String qualifiedName) {
        return unqualify(qualifiedName, ".");
    }

    public static String unqualify(String qualifiedName, String seperator) {
        return qualifiedName.substring(qualifiedName.lastIndexOf(seperator) + 1);
    }

    public static String qualifier(String qualifiedName) {
        int loc = qualifiedName.lastIndexOf(".");
        return loc < 0 ? "" : qualifiedName.substring(0, loc);
    }

    public static String[] suffix(String[] columns, String suffix) {
        if (suffix == null) {
            return columns;
        } else {
            String[] qualified = new String[columns.length];

            for(int i = 0; i < columns.length; ++i) {
                qualified[i] = suffix(columns[i], suffix);
            }

            return qualified;
        }
    }

    public static String suffix(String name, String suffix) {
        return suffix == null ? name : name + suffix;
    }

    public static String[] prefix(String[] columns, String prefix) {
        if (prefix == null) {
            return columns;
        } else {
            String[] qualified = new String[columns.length];

            for(int i = 0; i < columns.length; ++i) {
                qualified[i] = prefix + columns[i];
            }

            return qualified;
        }
    }

    public static String prefix(String name, String prefix) {
        return prefix == null ? name : prefix + name;
    }

    public static boolean booleanValue(String tfString) {
        String trimmed = tfString.trim().toLowerCase();
        return trimmed.equals("true") || trimmed.equals("t");
    }

    public static String toString(Object[] array) {
        int len = array.length;
        if (len == 0) {
            return "";
        } else {
            StringBuffer buf = new StringBuffer(len * 12);

            for(int i = 0; i < len - 1; ++i) {
                buf.append(array[i]).append(", ");
            }

            return buf.append(array[len - 1]).toString();
        }
    }

    public static String[] multiply(String string, Iterator placeholders, Iterator replacements) {
        String[] result;
        for(result = new String[]{string}; placeholders.hasNext(); result = multiply(result, (String)placeholders.next(), (String[])((String[])replacements.next()))) {
        }

        return result;
    }

    private static String[] multiply(String[] strings, String placeholder, String[] replacements) {
        String[] results = new String[replacements.length * strings.length];
        int n = 0;

        for(int i = 0; i < replacements.length; ++i) {
            for(int j = 0; j < strings.length; ++j) {
                results[n++] = replaceOnce(strings[j], placeholder, replacements[i]);
            }
        }

        return results;
    }

    public static int count(String string, char character) {
        int n = 0;

        for(int i = 0; i < string.length(); ++i) {
            if (string.charAt(i) == character) {
                ++n;
            }
        }

        return n;
    }

    public static int countUnquoted(String string, char character) {
        if ('\'' == character) {
            throw new IllegalArgumentException("Unquoted count of quotes is invalid");
        } else {
            int count = 0;
            int stringLength = string == null ? 0 : string.length();
            boolean inQuote = false;

            for(int indx = 0; indx < stringLength; ++indx) {
                if (inQuote) {
                    if ('\'' == string.charAt(indx)) {
                        inQuote = false;
                    }
                } else if ('\'' == string.charAt(indx)) {
                    inQuote = true;
                } else if (string.charAt(indx) == character) {
                    ++count;
                }
            }

            return count;
        }
    }

    public static boolean isBlank(String str) {
        boolean b = true;
        if (str == null) {
            b = true;
        } else {
            int strLen = str.length();
            if (strLen == 0) {
                b = true;
            }

            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    b = false;
                    break;
                }
            }
        }

        return b;
    }

    public static boolean isNotBlank(String str) {
        int strLen = 0;
        if (str != null) {
            strLen = str.length();
        }

        if (str != null && strLen != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }

    public static boolean isNotEmpty(String string) {
        return string != null && string.length() > 0;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String qualify(String name, String prefix) {
        return name.startsWith("'") ? name : (new StringBuffer(prefix.length() + name.length() + 1)).append(prefix).append('.').append(name).toString();
    }

    public static String[] qualify(String[] names, String prefix) {
        if (prefix == null) {
            return names;
        } else {
            int len = names.length;
            String[] qualified = new String[len];

            for(int i = 0; i < len; ++i) {
                qualified[i] = qualify(prefix, names[i]);
            }

            return qualified;
        }
    }

    public static int firstIndexOfChar(String sqlString, String string, int startindex) {
        int matchAt = -1;

        for(int i = 0; i < string.length(); ++i) {
            int curMatch = sqlString.indexOf(string.charAt(i), startindex);
            if (curMatch >= 0) {
                if (matchAt == -1) {
                    matchAt = curMatch;
                } else {
                    matchAt = Math.min(matchAt, curMatch);
                }
            }
        }

        return matchAt;
    }

    public static String truncate(String string, int length, boolean appendSuspensionPoints) {
        if (!isEmpty(string) && length >= 0) {
            if (length == 0) {
                return "";
            } else {
                int strLength = string.length();
                int byteLength = byteLength(string);
                length *= 2;
                boolean needSus = false;
                if (appendSuspensionPoints && byteLength >= length) {
                    needSus = true;
                    length -= 2;
                }

                StringBuffer result = new StringBuffer();
                int count = 0;

                for(int i = 0; i < strLength && count < length; ++i) {
                    char c = string.charAt(i);
                    if (isLetter(c)) {
                        result.append(c);
                        ++count;
                    } else if (count == length - 1) {
                        result.append(" ");
                        ++count;
                    } else {
                        result.append(c);
                        count += 2;
                    }
                }

                if (needSus) {
                    result.append("...");
                }

                return result.toString();
            }
        } else {
            return string;
        }
    }

    public static boolean isLetter(char c) {
        int k = 128;
        return c / k == 0;
    }

    public static int byteLength(String s) {
        char[] c = s.toCharArray();
        int len = 0;

        for(int i = 0; i < c.length; ++i) {
            if (isLetter(c[i])) {
                ++len;
            } else {
                len += 2;
            }
        }

        return len;
    }

    public static String truncate(String string, int length) {
        if (isEmpty(string)) {
            return string;
        } else {
            return string.length() <= length ? string : string.substring(0, length);
        }
    }

    public static String leftTrim(String value) {
        String result = value;
        if (value == null) {
            return value;
        } else {
            char[] ch = value.toCharArray();
            int index = -1;

            for(int i = 0; i < ch.length && Character.isWhitespace(ch[i]); index = i++) {
            }

            if (index != -1) {
                result = value.substring(index + 1);
            }

            return result;
        }
    }

    public static String rightTrim(String value) {
        String result = value;
        if (value == null) {
            return value;
        } else {
            char[] ch = value.toCharArray();
            int endIndex = -1;

            for(int i = ch.length - 1; i > -1 && Character.isWhitespace(ch[i]); endIndex = i--) {
            }

            if (endIndex != -1) {
                result = value.substring(0, endIndex);
            }

            return result;
        }
    }

    public static String n2s(String source) {
        return source != null ? source : "";
    }

    public static String n2s(String source, String defaultStr) {
        return source != null ? source : defaultStr;
    }

    public static final String toGb2312(String source) {
        String temp = null;
        if (source != null && !source.equals("")) {
            try {
                temp = new String(source.getBytes("8859_1"), "GB2312");
            } catch (Exception var3) {
                log.error("转换字符串为gb2312编码出错", var3);
            }

            return temp;
        } else {
            return source;
        }
    }

    public static final String toGBK(String source) {
        String temp = null;
        if (source != null && !source.equals("")) {
            try {
                temp = new String(source.getBytes("8859_1"), "GBK");
            } catch (Exception var3) {
                log.error("Convert code Error", var3);
            }

            return temp;
        } else {
            return source;
        }
    }

    public static final String to8859(String source) {
        String temp = null;
        if (source != null && !source.equals("")) {
            try {
                temp = new String(source.getBytes("GBK"), "8859_1");
            } catch (Exception var3) {
                log.error("Convert code Error", var3);
            }

            return temp;
        } else {
            return source;
        }
    }

    public static String chineseToUnicode(String source) {
        if (isEmpty(source)) {
            return source;
        } else {
            String unicode = null;
            String temp = null;

            for(int i = 0; i < source.length(); ++i) {
                temp = "\\u" + Integer.toHexString(source.charAt(i));
                unicode = unicode == null ? temp : unicode + temp;
            }

            return unicode;
        }
    }

    public static String toScript(String str) {
        if (str == null) {
            return null;
        } else {
            String html = new String(str);
            html = replace(html, "\"", "\\\"");
            html = replace(html, "\r\n", "\n");
            html = replace(html, "\n", "\\n");
            html = replace(html, "\t", "    ");
            html = replace(html, "'", "\\'");
            html = replace(html, "  ", " &nbsp;");
            html = replace(html, "</script>", "<\\/script>");
            html = replace(html, "</SCRIPT>", "<\\/SCRIPT>");
            return html;
        }
    }

    public static String trim(String s) {
        return s == null ? s : s.trim();
    }

    public static int strTrim(String source, int defaultValue) {
        if (isEmpty(source)) {
            return defaultValue;
        } else {
            try {
                source = source.trim();
                int value = new Integer(source);
                return value;
            } catch (Exception var3) {
                log.error("数字转换出错，请检查数据来源。返回默认值", var3);
                return defaultValue;
            }
        }
    }

    public static String strTrim(String source, String defaultValue) {
        if (isEmpty(source)) {
            return defaultValue;
        } else {
            try {
                source = source.trim();
                return source;
            } catch (Exception var3) {
                log.error("这个地方永远也不可能出错啊，这代码。。。。", var3);
                return defaultValue;
            }
        }
    }

    public static String encodeURL(String source) {
        if (source == null) {
            return null;
        } else {
            String html = new String(source);
            html = replace(html, "<", "&lt;");
            html = replace(html, ">", "&gt;");
            html = replace(html, "\"", "&quot;");
            html = replace(html, " ", "&nbsp;");
            html = replace(html, "'", "&acute;");
            html = replace(html, "\\", "&#092;");
            html = replace(html, "&", "&amp;");
            html = replace(html, "\r", "");
            html = replace(html, "\n", "");
            html = replace(html, "(", "&#40;");
            html = replace(html, ")", "&#41;");
            html = replace(html, "[", "&#91;");
            html = replace(html, "]", "&#93;");
            html = replace(html, ";", "&#59;");
            html = replace(html, "/", "&#47;");
            return html;
        }
    }

    public static String encodeHtml(String source) {
        if (source == null) {
            return null;
        } else {
            String html = new String(source);
            html = replace(html, "&", "&amp;");
            html = replace(html, "<", "&lt;");
            html = replace(html, ">", "&gt;");
            html = replace(html, "\"", "&quot;");
            html = replace(html, " ", "&nbsp;");
            html = replace(html, "'", "&acute;");
            return html;
        }
    }

    public static String decodeHtml(String source) {
        if (source == null) {
            return null;
        } else {
            String html = new String(source);
            html = replace(html, "&amp;", "&");
            html = replace(html, "&lt;", "<");
            html = replace(html, "&gt;", ">");
            html = replace(html, "&quot;", "\"");
            html = replace(html, " ", "&nbsp;");
            html = replace(html, "\r\n", "\n");
            html = replace(html, "\n", "<br>\n");
            html = replace(html, "\t", "    ");
            html = replace(html, "  ", " &nbsp;");
            return html;
        }
    }

    public static boolean isBoolean(String source) {
        return source.equalsIgnoreCase("true") || source.equalsIgnoreCase("false");
    }

    public static String lastCharTrim(String str, String strMove) {
        if (isEmpty(str)) {
            return "";
        } else {
            String newStr = "";
            if (str.lastIndexOf(strMove) != -1 && str.lastIndexOf(strMove) == str.length() - 1) {
                newStr = str.substring(0, str.lastIndexOf(strMove));
            }

            return newStr;
        }
    }

    public static String textFmtToHtmlFmt(String content) {
        content = replace(content, " ", "&nbsp;");
        content = replace(content, "\r\n", "<br>");
        content = replace(content, "\n", "<br>");
        return content;
    }

    public static String toLowerStr(String strIn) {
        String strOut = new String();
        int len = strIn.length();

        for(int i = 0; i < len; ++i) {
            char ch = strIn.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                ch = (char)(ch - 65 + 97);
            }

            strOut = strOut + ch;
        }

        return strOut;
    }

    public static String toUpperStr(String strIn) {
        String strOut = new String();
        int len = strIn.length();

        for(int i = 0; i < len; ++i) {
            char ch = strIn.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                ch = (char)(ch - 97 + 65);
            }

            strOut = strOut + ch;
        }

        return strOut;
    }

    public static boolean isEmail(String str) {
        if (isEmpty(str)) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
            Matcher matcher = pattern.matcher(str);
            return matcher.matches();
        }
    }

    public static boolean isMoblie(String str) {
        if (isEmpty(str)) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("^(13|14|15|17|18)[0-9]{9}$");
            Matcher matcher = pattern.matcher(str);
            return matcher.matches();
        }
    }

    public static String currencyShortFor(String original) {
        if (isBlank(original)) {
            return "";
        } else {
            String shortFor = "";
            double shortForValue = 0.0;
            DecimalFormat df = new DecimalFormat("#.00");

            try {
                double account = Double.parseDouble(original);
                if (account / 1.0E8 > 1.0) {
                    shortForValue = account / 1.0E8;
                    shortFor = df.format(shortForValue) + "亿";
                } else if (account / 10000.0 > 1.0) {
                    shortForValue = account / 10000.0;
                    shortFor = df.format(shortForValue) + "万";
                } else {
                    shortFor = original;
                }
            } catch (NumberFormatException var7) {
                log.error("字符串[" + original + "]转换成数字出错", var7);
            }

            return shortFor;
        }
    }

    public static String formatDate(String date) {
        if (!isBlank(date) && date.length() >= 8) {
            StringBuffer dateBuf = new StringBuffer();
            dateBuf.append(date.substring(0, 4));
            dateBuf.append("-");
            dateBuf.append(date.substring(4, 6));
            dateBuf.append("-");
            dateBuf.append(date.substring(6, 8));
            return dateBuf.toString();
        } else {
            return "";
        }
    }

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^\\d+(\\.0)?$", 2);
        return pattern.matcher(str).matches();
    }

    public static String substring(String string, int byteCount) throws UnsupportedEncodingException {
        if (isBlank(string)) {
            return string;
        } else {
            byte[] bytes = string.getBytes("Unicode");
            int viewBytes = 0;

            int ucs2Bytes;
            for(ucs2Bytes = 2; ucs2Bytes < bytes.length && viewBytes < byteCount; ++ucs2Bytes) {
                if (ucs2Bytes % 2 == 1) {
                    ++viewBytes;
                } else if (bytes[ucs2Bytes] != 0) {
                    ++viewBytes;
                }
            }

            if (ucs2Bytes % 2 == 1) {
                ++ucs2Bytes;
            }

            String result = new String(bytes, 0, ucs2Bytes, "Unicode");
            if (bytes.length > ucs2Bytes) {
                result = result + "...";
            }

            return result;
        }
    }

    public static String[] splite(String str, int length) {
        if (isEmpty(str)) {
            return null;
        } else {
            String[] strArr = new String[(str.length() + length - 1) / length];

            for(int i = 0; i < strArr.length; ++i) {
                if (str.length() > i * length + length - 1) {
                    strArr[i] = str.substring(i * length, i * length + length - 1);
                } else {
                    strArr[i] = str.substring(i * length);
                }
            }

            return strArr;
        }
    }

    public static String toUpOneChar(String str, int index) {
        return toUpOrLowOneChar(str, index, 1);
    }

    public static String toLowOneChar(String str, int index) {
        return toUpOrLowOneChar(str, index, 0);
    }

    public static String toUpOrLowOneChar(String str, int index, int upOrLow) {
        if (isNotEmpty(str) && index > -1 && index < str.length()) {
            char[] chars = str.toCharArray();
            if (upOrLow == 1) {
                chars[index] = Character.toUpperCase(chars[index]);
            } else {
                chars[index] = Character.toLowerCase(chars[index]);
            }

            return new String(chars);
        } else {
            return str;
        }
    }
}
