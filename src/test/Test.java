import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cotech.enums.Status;
import com.cotech.model.Circle;
import com.cotech.model.TRes;
import com.cotech.util.JsonUtil;
import com.cotech.util.ParamCheck;
import com.cotech.util.WrapJson;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

    public static void main(String[] args) {
        String s = "{\"circle_x\":\"300\",\"circle_y\":\"400\",\"circle_r\":\"240\",\"coordinate\":\"270,152,298,152,316,152,326,152,340,152,348,154,358,156,366,160,370,166,382,172,390,184,394,190,404,196,410,204,416,212,420,218,426,224,428,228,430,230,432,234,432,236,432,240,432,242,434,244,440,248,450,256,460,262,466,266,472,266,476,268,478,272,480,274,484,274,484,278,492,290,500,302,512,318,524,336,528,346,534,362,538,372,540,378,542,388,542,400,542,406,542,412,542,414,542,418,542,420,542,424,542,430,540,438,536,454,532,462,528,470,520,486,512,500,496,516,484,530,470,544,456,556,444,566,430,580,418,592,404,604,392,614,386,618,376,624,364,628,360,628,354,630,336,630,318,628,298,622,270,612,258,606,242,598,234,592,226,584,220,572,214,560,208,546,202,526,194,506,186,486,180,462,178,442,172,424,170,410,164,384,162,368,158,352,156,336,156,318,156,302,156,284,160,270,166,258,174,246,184,240,186,236,194,230,196,228,202,226,216,216,222,214,230,212,244,202,250,200,258,194,264,188,266,188,270,184,270,182,270,178,270,176,270,172,270,170,270,166,270,162,270,160\",\"time_len\":\"2555\",\"device\":\"Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1\",\"referee\":\"54\",\"user_id\":\"0\",\"group\":\"1234\"}\n";
        TRes res = new TRes();
        Circle circle = new Circle();
        try{
            JSONObject paramJson = (JSONObject) JSON.parse(s);
            circle.setCircle_r(ParamCheck.paramNotNull(Double.valueOf((String) paramJson.get("circle_r"))));
            circle.setCircle_y(ParamCheck.paramNotNull(Double.valueOf((String) paramJson.get("circle_y"))));
            circle.setCircle_x(ParamCheck.paramNotNull(Double.valueOf((String) paramJson.get("circle_x"))));
            res.setCoordinate(ParamCheck.paramNotEmptyNotNull((String) paramJson.get("coordinate")));
            res.setTime_len(ParamCheck.paramNotZeroNotNull(Long.valueOf((String) paramJson.get("time_len"))));
            res.setDeviation("111");
            res.setScore((long) Math.abs(1000-Double.parseDouble(res.getDeviation())*10));
            res.setGold((long) Double.parseDouble(res.getDeviation())/10+10);
            res.setDevice((String) paramJson.get("device"));
            res.setGroup((String) paramJson.get("group"));
            res.setCreate_time((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
            res.setReferee(Long.valueOf((String) paramJson.get("referee")));
            res.setUser_id(Long.valueOf((String) paramJson.get("user_id")));
            res.setLocation_id(Hashing.md5().newHasher().putString(res.getCreate_time(), Charsets.UTF_8).hash().toString());
        } catch (Exception e){
            System.out.println(e.getMessage()+res.toString());
        }
    }
}