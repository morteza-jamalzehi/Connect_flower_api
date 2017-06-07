package com.hanselandpetal.catalog.parser;

import com.hanselandpetal.catalog.model.Flower;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class FlowerParser {

    public static List<Flower> parse(String xml) throws IOException {
        try {
            String currentTagName = "";
            boolean inDataItemTag = false;
            List<Flower> flowerList = new ArrayList<>();
            Flower flower = null;

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(xml));
            //where am i
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        currentTagName = xpp.getName();
                        if (currentTagName.equals("product")) {
                            inDataItemTag = true;
                            flower = new Flower();
                            flowerList.add(flower);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (xpp.getName().equals("product")) {
                            inDataItemTag = false;
                        }
                        currentTagName = "";
                        break;

                    case XmlPullParser.TEXT:
                        if (inDataItemTag && flower != null) {

                            switch (currentTagName) {
                                case "productId":
                                    flower.setProductId(Integer.parseInt(xpp.getText()));
                                    break;
                                case "name":
                                    flower.setName(xpp.getText());
                                    break;
                                case "instructions":
                                    flower.setInstructions(xpp.getText());
                                    break;
                                case "category":
                                    flower.setCategory(xpp.getText());
                                    break;
                                case "price":
                                    flower.setPrice(Double.parseDouble(xpp.getText()));
                                    break;
                                case "photo":
                                    flower.setPhoto(xpp.getText());
                                default:
                                    break;

                            }

                        }

                        break;

                }
                eventType = xpp.next();
            }

            return flowerList;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return null;
        }
    }
}
