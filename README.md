# Connect_flower_api
fetching data from api and parsing it
we need an api that represent data either in xml or json format.
to connect and download data from that api i used HttpURLConnection class
i created a model add associated with xml field to handle the data and show it in layout
and finnaly i created a parser class for parse xml using XmlPullParser and looping through the content and
handle the content using START_TAG,END_TAG,END_DOCUMENT and TEXT. 
