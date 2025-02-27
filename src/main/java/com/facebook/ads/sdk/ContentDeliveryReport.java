/**
 * Copyright (c) 2015-present, Facebook, Inc. All rights reserved.
 *
 * You are hereby granted a non-exclusive, worldwide, royalty-free license to
 * use, copy, modify, and distribute this software in source code or binary
 * form for use in connection with the web services and APIs provided by
 * Facebook.
 *
 * As with any software that integrates with the Facebook platform, your use
 * of this software is subject to the Facebook Developer Principles and
 * Policies [http://developers.facebook.com/policy/]. This copyright notice
 * shall be included in all copies or substantial portions of the software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 *
 */

package com.facebook.ads.sdk;

import java.io.File;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import com.facebook.ads.sdk.APIException.MalformedResponseException;

/**
 * This class is auto-generated.
 *
 * For any issues or feature requests related to this class, please let us know
 * on github and we'll fix in our codegen framework. We'll not be able to accept
 * pull request for this class.
 *
 */
public class ContentDeliveryReport extends APINode {
  @SerializedName("content_name")
  private String mContentName = null;
  @SerializedName("content_url")
  private String mContentUrl = null;
  @SerializedName("creator_name")
  private String mCreatorName = null;
  @SerializedName("creator_url")
  private String mCreatorUrl = null;
  @SerializedName("estimated_impressions")
  private Long mEstimatedImpressions = null;
  protected static Gson gson = null;

  public ContentDeliveryReport() {
  }

  public String getId() {
    return null;
  }
  public static ContentDeliveryReport loadJSON(String json, APIContext context, String header) {
    ContentDeliveryReport contentDeliveryReport = getGson().fromJson(json, ContentDeliveryReport.class);
    if (context.isDebug()) {
      JsonParser parser = new JsonParser();
      JsonElement o1 = parser.parse(json);
      JsonElement o2 = parser.parse(contentDeliveryReport.toString());
      if (o1.getAsJsonObject().get("__fb_trace_id__") != null) {
        o2.getAsJsonObject().add("__fb_trace_id__", o1.getAsJsonObject().get("__fb_trace_id__"));
      }
      if (!o1.equals(o2)) {
        context.log("[Warning] When parsing response, object is not consistent with JSON:");
        context.log("[JSON]" + o1);
        context.log("[Object]" + o2);
      }
    }
    contentDeliveryReport.context = context;
    contentDeliveryReport.rawValue = json;
    contentDeliveryReport.header = header;
    return contentDeliveryReport;
  }

  public static APINodeList<ContentDeliveryReport> parseResponse(String json, APIContext context, APIRequest request, String header) throws MalformedResponseException {
    APINodeList<ContentDeliveryReport> contentDeliveryReports = new APINodeList<ContentDeliveryReport>(request, json, header);
    JsonArray arr;
    JsonObject obj;
    JsonParser parser = new JsonParser();
    Exception exception = null;
    try{
      JsonElement result = parser.parse(json);
      if (result.isJsonArray()) {
        // First, check if it's a pure JSON Array
        arr = result.getAsJsonArray();
        for (int i = 0; i < arr.size(); i++) {
          contentDeliveryReports.add(loadJSON(arr.get(i).getAsJsonObject().toString(), context, header));
        };
        return contentDeliveryReports;
      } else if (result.isJsonObject()) {
        obj = result.getAsJsonObject();
        if (obj.has("data")) {
          if (obj.has("paging")) {
            JsonObject paging = obj.get("paging").getAsJsonObject();
            if (paging.has("cursors")) {
                JsonObject cursors = paging.get("cursors").getAsJsonObject();
                String before = cursors.has("before") ? cursors.get("before").getAsString() : null;
                String after = cursors.has("after") ? cursors.get("after").getAsString() : null;
                contentDeliveryReports.setCursors(before, after);
            }
            String previous = paging.has("previous") ? paging.get("previous").getAsString() : null;
            String next = paging.has("next") ? paging.get("next").getAsString() : null;
            contentDeliveryReports.setPaging(previous, next);
            if (context.hasAppSecret()) {
              contentDeliveryReports.setAppSecret(context.getAppSecretProof());
            }
          }
          if (obj.get("data").isJsonArray()) {
            // Second, check if it's a JSON array with "data"
            arr = obj.get("data").getAsJsonArray();
            for (int i = 0; i < arr.size(); i++) {
              contentDeliveryReports.add(loadJSON(arr.get(i).getAsJsonObject().toString(), context, header));
            };
          } else if (obj.get("data").isJsonObject()) {
            // Third, check if it's a JSON object with "data"
            obj = obj.get("data").getAsJsonObject();
            boolean isRedownload = false;
            for (String s : new String[]{"campaigns", "adsets", "ads"}) {
              if (obj.has(s)) {
                isRedownload = true;
                obj = obj.getAsJsonObject(s);
                for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
                  contentDeliveryReports.add(loadJSON(entry.getValue().toString(), context, header));
                }
                break;
              }
            }
            if (!isRedownload) {
              contentDeliveryReports.add(loadJSON(obj.toString(), context, header));
            }
          }
          return contentDeliveryReports;
        } else if (obj.has("images")) {
          // Fourth, check if it's a map of image objects
          obj = obj.get("images").getAsJsonObject();
          for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
              contentDeliveryReports.add(loadJSON(entry.getValue().toString(), context, header));
          }
          return contentDeliveryReports;
        } else {
          // Fifth, check if it's an array of objects indexed by id
          boolean isIdIndexedArray = true;
          for (Map.Entry entry : obj.entrySet()) {
            String key = (String) entry.getKey();
            if (key.equals("__fb_trace_id__")) {
              continue;
            }
            JsonElement value = (JsonElement) entry.getValue();
            if (
              value != null &&
              value.isJsonObject() &&
              value.getAsJsonObject().has("id") &&
              value.getAsJsonObject().get("id") != null &&
              value.getAsJsonObject().get("id").getAsString().equals(key)
            ) {
              contentDeliveryReports.add(loadJSON(value.toString(), context, header));
            } else {
              isIdIndexedArray = false;
              break;
            }
          }
          if (isIdIndexedArray) {
            return contentDeliveryReports;
          }

          // Sixth, check if it's pure JsonObject
          contentDeliveryReports.clear();
          contentDeliveryReports.add(loadJSON(json, context, header));
          return contentDeliveryReports;
        }
      }
    } catch (Exception e) {
      exception = e;
    }
    throw new MalformedResponseException(
      "Invalid response string: " + json,
      exception
    );
  }

  @Override
  public APIContext getContext() {
    return context;
  }

  @Override
  public void setContext(APIContext context) {
    this.context = context;
  }

  @Override
  public String toString() {
    return getGson().toJson(this);
  }


  public String getFieldContentName() {
    return mContentName;
  }

  public ContentDeliveryReport setFieldContentName(String value) {
    this.mContentName = value;
    return this;
  }

  public String getFieldContentUrl() {
    return mContentUrl;
  }

  public ContentDeliveryReport setFieldContentUrl(String value) {
    this.mContentUrl = value;
    return this;
  }

  public String getFieldCreatorName() {
    return mCreatorName;
  }

  public ContentDeliveryReport setFieldCreatorName(String value) {
    this.mCreatorName = value;
    return this;
  }

  public String getFieldCreatorUrl() {
    return mCreatorUrl;
  }

  public ContentDeliveryReport setFieldCreatorUrl(String value) {
    this.mCreatorUrl = value;
    return this;
  }

  public Long getFieldEstimatedImpressions() {
    return mEstimatedImpressions;
  }

  public ContentDeliveryReport setFieldEstimatedImpressions(Long value) {
    this.mEstimatedImpressions = value;
    return this;
  }



  public static enum EnumPlatform {
      @SerializedName("AUDIENCE_NETWORK")
      VALUE_AUDIENCE_NETWORK("AUDIENCE_NETWORK"),
      @SerializedName("FACEBOOK")
      VALUE_FACEBOOK("FACEBOOK"),
      @SerializedName("HIDDEN_AAA")
      VALUE_HIDDEN_AAA("HIDDEN_AAA"),
      @SerializedName("INSTAGRAM")
      VALUE_INSTAGRAM("INSTAGRAM"),
      @SerializedName("MESSENGER")
      VALUE_MESSENGER("MESSENGER"),
      @SerializedName("OCULUS")
      VALUE_OCULUS("OCULUS"),
      @SerializedName("UNKNOWN")
      VALUE_UNKNOWN("UNKNOWN"),
      @SerializedName("WHATSAPP")
      VALUE_WHATSAPP("WHATSAPP"),
      ;

      private String value;

      private EnumPlatform(String value) {
        this.value = value;
      }

      @Override
      public String toString() {
        return value;
      }
  }

  public static enum EnumPosition {
      @SerializedName("ALL_PLACEMENTS")
      VALUE_ALL_PLACEMENTS("ALL_PLACEMENTS"),
      @SerializedName("AN_CLASSIC")
      VALUE_AN_CLASSIC("AN_CLASSIC"),
      @SerializedName("BIZ_DISCO_FEED")
      VALUE_BIZ_DISCO_FEED("BIZ_DISCO_FEED"),
      @SerializedName("FACEBOOK_GROUPS_FEED")
      VALUE_FACEBOOK_GROUPS_FEED("FACEBOOK_GROUPS_FEED"),
      @SerializedName("FACEBOOK_REELS")
      VALUE_FACEBOOK_REELS("FACEBOOK_REELS"),
      @SerializedName("FACEBOOK_STORIES")
      VALUE_FACEBOOK_STORIES("FACEBOOK_STORIES"),
      @SerializedName("FEED")
      VALUE_FEED("FEED"),
      @SerializedName("GROUPS")
      VALUE_GROUPS("GROUPS"),
      @SerializedName("HIDDEN_AAA")
      VALUE_HIDDEN_AAA("HIDDEN_AAA"),
      @SerializedName("INSTAGRAM_EXPLORE")
      VALUE_INSTAGRAM_EXPLORE("INSTAGRAM_EXPLORE"),
      @SerializedName("INSTAGRAM_IGTV")
      VALUE_INSTAGRAM_IGTV("INSTAGRAM_IGTV"),
      @SerializedName("INSTAGRAM_REELS")
      VALUE_INSTAGRAM_REELS("INSTAGRAM_REELS"),
      @SerializedName("INSTAGRAM_SHOP")
      VALUE_INSTAGRAM_SHOP("INSTAGRAM_SHOP"),
      @SerializedName("INSTAGRAM_STORIES")
      VALUE_INSTAGRAM_STORIES("INSTAGRAM_STORIES"),
      @SerializedName("INSTANT_ARTICLE")
      VALUE_INSTANT_ARTICLE("INSTANT_ARTICLE"),
      @SerializedName("INSTREAM_VIDEO")
      VALUE_INSTREAM_VIDEO("INSTREAM_VIDEO"),
      @SerializedName("JOBS_BROWSER")
      VALUE_JOBS_BROWSER("JOBS_BROWSER"),
      @SerializedName("MARKETPLACE")
      VALUE_MARKETPLACE("MARKETPLACE"),
      @SerializedName("MESSENGER_INBOX")
      VALUE_MESSENGER_INBOX("MESSENGER_INBOX"),
      @SerializedName("MESSENGER_STORIES")
      VALUE_MESSENGER_STORIES("MESSENGER_STORIES"),
      @SerializedName("OCULUS_REWARDED_VIDEO")
      VALUE_OCULUS_REWARDED_VIDEO("OCULUS_REWARDED_VIDEO"),
      @SerializedName("OCULUS_TWILIGHT_DEVELOPER_UPDATE")
      VALUE_OCULUS_TWILIGHT_DEVELOPER_UPDATE("OCULUS_TWILIGHT_DEVELOPER_UPDATE"),
      @SerializedName("OCULUS_TWILIGHT_FEED")
      VALUE_OCULUS_TWILIGHT_FEED("OCULUS_TWILIGHT_FEED"),
      @SerializedName("OCULUS_TWILIGHT_FEED_SPOTLIGHT")
      VALUE_OCULUS_TWILIGHT_FEED_SPOTLIGHT("OCULUS_TWILIGHT_FEED_SPOTLIGHT"),
      @SerializedName("OCULUS_TWILIGHT_SEARCH")
      VALUE_OCULUS_TWILIGHT_SEARCH("OCULUS_TWILIGHT_SEARCH"),
      @SerializedName("OCULUS_VR_APPS")
      VALUE_OCULUS_VR_APPS("OCULUS_VR_APPS"),
      @SerializedName("OTHERS")
      VALUE_OTHERS("OTHERS"),
      @SerializedName("REWARDED_VIDEO")
      VALUE_REWARDED_VIDEO("REWARDED_VIDEO"),
      @SerializedName("RIGHT_HAND_COLUMN")
      VALUE_RIGHT_HAND_COLUMN("RIGHT_HAND_COLUMN"),
      @SerializedName("SEARCH")
      VALUE_SEARCH("SEARCH"),
      @SerializedName("STATUS")
      VALUE_STATUS("STATUS"),
      @SerializedName("STICKERS")
      VALUE_STICKERS("STICKERS"),
      @SerializedName("SUGGESTED_VIDEO")
      VALUE_SUGGESTED_VIDEO("SUGGESTED_VIDEO"),
      @SerializedName("UNKNOWN")
      VALUE_UNKNOWN("UNKNOWN"),
      @SerializedName("VIDEO_FEEDS")
      VALUE_VIDEO_FEEDS("VIDEO_FEEDS"),
      ;

      private String value;

      private EnumPosition(String value) {
        this.value = value;
      }

      @Override
      public String toString() {
        return value;
      }
  }


  synchronized /*package*/ static Gson getGson() {
    if (gson != null) {
      return gson;
    } else {
      gson = new GsonBuilder()
        .excludeFieldsWithModifiers(Modifier.STATIC)
        .excludeFieldsWithModifiers(Modifier.PROTECTED)
        .disableHtmlEscaping()
        .create();
    }
    return gson;
  }

  public ContentDeliveryReport copyFrom(ContentDeliveryReport instance) {
    this.mContentName = instance.mContentName;
    this.mContentUrl = instance.mContentUrl;
    this.mCreatorName = instance.mCreatorName;
    this.mCreatorUrl = instance.mCreatorUrl;
    this.mEstimatedImpressions = instance.mEstimatedImpressions;
    this.context = instance.context;
    this.rawValue = instance.rawValue;
    return this;
  }

  public static APIRequest.ResponseParser<ContentDeliveryReport> getParser() {
    return new APIRequest.ResponseParser<ContentDeliveryReport>() {
      public APINodeList<ContentDeliveryReport> parseResponse(String response, APIContext context, APIRequest<ContentDeliveryReport> request, String header) throws MalformedResponseException {
        return ContentDeliveryReport.parseResponse(response, context, request, header);
      }
    };
  }
}
