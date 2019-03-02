package com.mobike.android_architecture.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.*;
import android.text.style.*;

import android.util.Log;
import org.xml.sax.XMLReader;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by chenheneng@xiaoyouzi.com on 2017/8/29 17:49
 */

public class HtmlTagHandler implements Html.TagHandler {
    String TAG = getClass().getSimpleName();
    //    private static final String TAG_BLUE_FONT = "bluefont";
    private static final String TAG_CUSTOM_FONT = "customfont";
    private static final String TAG_SPAN = "espan";
    private static final String TAG_STRIKE_S = "s";
    private static final String TAG_STRIKE = "strike";
    private static final String TAG_DIVIDER_LINE = "hr";
    private static final String TAG_A_HERF = "ahref";

    private int diviverLineHeight = 5;
    final HashMap<String, String> attributes = new HashMap<String, String>();
    private List<BaseSpan> spanList = new ArrayList<>();

    @Override
    public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
        if (opening) {
            processAttributes(xmlReader);
            handleStartTag(tag, output);
        } else {
            handleEndTag(tag, output);
        }
    }

    private void handleStartTag(String tag, Editable output) {
//        Log.i(TAG, "handleStartTag: tag = " + tag + " output = " + output.toString() + " length = " + output.length());
        if (tag.equalsIgnoreCase(TAG_CUSTOM_FONT)) {
            startFont(tag, output);
        } else if (tag.equalsIgnoreCase(TAG_SPAN)) {
//            handleP(output);
            startSpan(tag, output);
        } else if (tag.equalsIgnoreCase(TAG_STRIKE_S) || tag.equalsIgnoreCase(TAG_STRIKE)) {
            start(output, new Strike());
        } else if (tag.equalsIgnoreCase(TAG_A_HERF)) {
//            handleP(output);
            startAherf(tag, output);
        } else {

        }
    }


    private void handleEndTag(String tag, Editable output) {
//        Log.i(TAG, "handleEndTag: tag = " + tag + " output = " + output.toString() + " length = " + output.length());
        if (tag.equalsIgnoreCase(TAG_CUSTOM_FONT)) {
            endFont(tag, output);
        } else if (tag.equalsIgnoreCase(TAG_SPAN)) {
//            handleP(output);
            endSpan(output, new QuoteSpan());
        } else if (tag.equalsIgnoreCase(TAG_STRIKE_S) || tag.equalsIgnoreCase(TAG_STRIKE)) {
            end(output, Strike.class, new StrikethroughSpan());
        } else if (tag.equalsIgnoreCase(TAG_DIVIDER_LINE)) {
//            handleP(output);
            endDividerLine(output);
        } else if (tag.equalsIgnoreCase(TAG_A_HERF)) {
//            handleP(output);
            endAherf(tag, output);
        } else {

        }
    }

    /**
     * 分割线 hr
     *
     * @param output
     */
    private void endDividerLine(Editable output) {

        Drawable drawable = null;

        int len = output.length();
        output.append("\uFFFC");
        try {
            Resources res = Resources.getSystem();
            int width = res.getDisplayMetrics().widthPixels;

            Bitmap divierLine = Bitmap.createBitmap(width, diviverLineHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(divierLine);
            Paint paint = new Paint();
            paint.setColor(Color.GRAY);
            paint.setFlags(1);
            paint.setStyle(Paint.Style.FILL);
            paint.setStrokeWidth(3);
            canvas.drawLine(0, diviverLineHeight / 2, width, diviverLineHeight / 2, paint);

            drawable = new BitmapDrawable(divierLine);
            drawable.setBounds(0, 0, width, diviverLineHeight);
            output.setSpan(new ImageSpan(drawable), len, output.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            output.append("\n");
        } catch (Exception e) {
            Log.d(getClass().getSimpleName(), e.toString());
        }
    }

    private static void start(Editable text, Object mark) {
        int len = text.length();
        text.setSpan(mark, len, len, Spannable.SPAN_MARK_MARK);
    }

    private static void end(Editable text, Class kind,
                            Object repl) {
        int len = text.length();
        Object obj = getLast(text, kind);
        int where = text.getSpanStart(obj);

        text.removeSpan(obj);

        if (where != len) {
            text.setSpan(repl, where, len, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    private static void handleP(Editable text) {
        int len = text.length();

        if (len >= 1 && text.charAt(len - 1) == '\n') {
            if (len >= 2 && text.charAt(len - 2) == '\n') {
                return;
            }

            text.append("\n");
            return;
        }

        if (len != 0) {
            text.append("\n\n");
        }
    }

    private static Object getLast(Spanned text, Class kind) {
        /*
         * This knows that the last returned object from getSpans()
         * will be the most recently added.
         */
        Object[] objs = text.getSpans(0, text.length(), kind);

        if (objs.length == 0) {
            return null;
        } else {
            return objs[objs.length - 1];
        }
    }


    public void startSpan(String tag, Editable output) {

        String style = attributes.get("style");
        int len = output.length();
        Span span = new Span(style);
        span.startIndex = len;
        spanList.add(span);
        output.setSpan(span, len, len, Spannable.SPAN_MARK_MARK);
    }

    public void endSpan(Editable output,
                        Object repl) {
        int len = output.length();
        Object obj = getLast(output, Span.class);
        int where = output.getSpanStart(obj);
        output.removeSpan(obj);
        if (where != len) {
            Span span = (Span) obj;
            span.endIndex = len;
            drawpanList(output);
        }
    }

    private void drawpanList(Editable output) {
        int size = spanList.size();
        for (int i = 0; i < size; i++) {
            BaseSpan curSpan = spanList.get(i);
            if (curSpan.endIndex > 0) {
                if (curSpan instanceof Href) {
                    drawAherfSpan(output, (Href) curSpan);
                } else {
                    drawSpan(output, (Span) curSpan);
                }
            }

        }
        BaseSpan first = spanList.get(0);
        if (first.endIndex > 0)
            spanList.clear();
    }

    private void drawSpan(Editable output, Span span) {
        if (!TextUtils.isEmpty(span.fontSize)) {
            int sizeSp = Integer.parseInt(span.fontSize);
            int size = sp2px(sizeSp);
            output.setSpan(new AbsoluteSizeSpan(size), span.startIndex, span.endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        if (!TextUtils.isEmpty(span.color)) {
            output.setSpan(new ForegroundColorSpan(Color.parseColor(span.color)), span.startIndex, span.endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        if (!TextUtils.isEmpty(span.bgColor)) {
            output.setSpan(new BackgroundColorSpan(Color.parseColor(span.bgColor)), span.startIndex, span.endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    public void startFont(String tag, Editable output) {
//        Log.i(TAG, "startFont: tag = " + tag);
        String color = attributes.get("color");
        String face = attributes.get("face");
        String size = attributes.get("size");
        if (!TextUtils.isEmpty(size))
            size = size.split("px")[0];

        int len = output.length();

        output.setSpan(new CustomFont(color, face, size), len, len, Spannable.SPAN_MARK_MARK);
    }

    public void endFont(String tag, Editable output) {
//        Log.i(TAG, "endFont: tag = " + tag);
        int len = output.length();
        Object obj = getLast(output, CustomFont.class);
        int where = output.getSpanStart(obj);

        output.removeSpan(obj);
        if (where != len) {
            CustomFont font = (CustomFont) obj;
            if (!TextUtils.isEmpty(font.mColor)) {
                output.setSpan(new ForegroundColorSpan(Color.parseColor(font.mColor)), where, len, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            if (!TextUtils.isEmpty(font.mSize)) {
                int sizeSp = Integer.parseInt(font.mSize);
                int size = sp2px(sizeSp);
                output.setSpan(new AbsoluteSizeSpan(size), where, len, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            if (!TextUtils.isEmpty(font.mFace)) {
                output.setSpan(new TypefaceSpan(font.mFace), where, len,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
    }

    private void startAherf(String tag, Editable text) {
        String href = attributes.get("href");

        int len = text.length();
        Href span = new Href(href);
        span.startIndex = len;
        spanList.add(span);
        text.setSpan(span, len, len, Spannable.SPAN_MARK_MARK);
    }

    private void endAherf(String tag, Editable text) {
        int len = text.length();
        Object obj = getLast(text, Href.class);
        int where = text.getSpanStart(obj);

        text.removeSpan(obj);
        Href href = (Href) obj;
        href.endIndex = len;
        drawpanList(text);
    }


    private void drawAherfSpan(Editable output, Href h) {
        if (h.mHref != null) {
            EcoTextLinkSpan linkSpan = new EcoTextLinkSpan(h.mHref);
            linkSpan.updateLinkColor(Color.BLUE);
            output.setSpan(linkSpan, h.startIndex, h.endIndex,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    private void processAttributes(final XMLReader xmlReader) {
        try {
            Field elementField = xmlReader.getClass().getDeclaredField("theNewElement");
            elementField.setAccessible(true);
            Object element = elementField.get(xmlReader);
            Field attsField = element.getClass().getDeclaredField("theAtts");
            attsField.setAccessible(true);
            Object atts = attsField.get(element);
            Field dataField = atts.getClass().getDeclaredField("data");
            dataField.setAccessible(true);
            String[] data = (String[]) dataField.get(atts);
            Field lengthField = atts.getClass().getDeclaredField("length");
            lengthField.setAccessible(true);
            int len = (Integer) lengthField.get(atts);

            for (int i = 0; i < len; i++) {
                attributes.put(data[i * 5 + 1], data[i * 5 + 4]);
            }
        } catch (Exception e) {

        }
    }

    private static class CustomFont {
        public String mColor;
        public String mFace;
        public String mSize;

        public CustomFont(String color, String face, String mSize) {
            mColor = color;
            mFace = face;
            this.mSize = mSize;
        }
    }

    private static class BaseSpan {
        int startIndex = -1;//  起始位置
        int endIndex = -1;// 结束位置
    }

    private static class Strike extends BaseSpan {
    }

    private static class Href extends BaseSpan {
        public String mHref;

        public Href(String href) {
            mHref = href;
        }
    }

    //style="font-size:16px;color:#337FE5;background-color:#FFE500;"
    private static class Span extends BaseSpan {
        private String colon = ";";
        private String semicolon = ":";
        private String style;
        String fontSize;
        String color;
        String bgColor;

        public Span(String style) {
            this.style = style;
            parseStyle(style);
        }

        public void parseStyle() {
            if (!TextUtils.isEmpty(style))
                parseStyle(style);
        }

        public void parseStyle(String style) {
            if (TextUtils.isEmpty(style)) return;
            this.style = style;
            try {

                if (style.contains(colon) && style.contains(semicolon)) {
                    String[] attrs = style.split(colon);
                    int length = attrs.length;
                    for (int i = 0; i < length; i++) {
                        try {
                            readParams(attrs[i]);
                        } catch (Exception e) {
                            Log.d(getClass().getSimpleName(), e.toString());
                        }
                    }
                }
            } catch (Exception e) {
                Log.d(getClass().getSimpleName(), e.toString());
            }

        }

        private void readParams(String attr) {
            if (TextUtils.isEmpty(attr)) return;
            if (!attr.contains(semicolon)) return;
            String[] params = attr.split(semicolon);
            if ("font-size".contains(params[0])) {
                if (!TextUtils.isEmpty(params[1]))
                    this.fontSize = params[1].split("px")[0];
            } else if ("color".contains(params[0])) {
                this.color = params[1].trim();
            } else if ("background-color".contains(params[0])) {
                this.bgColor = params[1].trim();
            } else {

            }
        }
    }


    public static int sp2px(float spValue) {
        Resources res = Resources.getSystem();
        float scale = res.getDisplayMetrics().scaledDensity;
        return (int) (spValue * scale + 0.5F);
    }
}
