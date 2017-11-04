import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cotech.enums.Status;
import com.cotech.model.Circle;
import com.cotech.model.TRes;
import com.cotech.service.GameService;
import com.cotech.util.JsonUtil;
import com.cotech.util.ParamCheck;
import com.cotech.util.WrapJson;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static com.google.common.base.Preconditions.checkNotNull;

public class Test {

    public static void main(String[] args) {
//        Node a = new Node("A",null,null);
//        Node b = new Node("B",null,null);
//        Node c = new Node("C",null,null);
//        Node d = new Node("D",null,null);
//        Node e = new Node("E",null,null);
//        Node f = new Node("F",null,null);
//        Node g = new Node("G",null,null);
//        Node h = new Node("H",null,null);
//        Node i = new Node("I",null,null);
//        Node j = new Node("J",null,null);
//
//        a.setLeft(b);
//        b.setRight(c);
//        c.setLeft(d);
//        c.setRight(e);
//        a.setRight(f);
//        f.setLeft(g);
//        g.setLeft(i);
//        g.setRight(j);
//        f.setRight(h);
//
//
//        houxubianli(a);
        String param = "{\"circle_x\":\"300\",\"circle_y\":\"400\",\"circle_r\":\"240\",\"coordinate\":\"362,222,370,222,390,228,404,230,430,244,456,260,474,280,494,302,506,316,530,354,540,374,550,394,552,412,558,426,562,444,564,466,564,482,564,492,558,510,554,516,542,528,534,534,522,540,510,542,494,548,474,558,456,564,430,570,402,574,368,576,334,576,304,576,276,568,250,560,224,548,200,536,180,524,162,512,146,496,128,478,126,474,114,460,106,434,106,422,106,410,112,392,120,378,136,360,146,344,158,330,170,314,182,304,188,302,202,292,208,286,226,276,232,274,238,270,244,270,252,264,256,260,258,258,262,258,264,258,268,258,268,254,270,254,272,254,276,252,278,252,278,250,282,250,284,248,286,248,290,246,292,242,300,234,314,226,320,222,324,222,326,218,330,218,336,218,344,218,356,222,366,222,384,230,392,232,396,234,398,234,398,236\",\"time_len\":\"2058\",\"device\":\"Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1\",\"referee\":\"0\",\"user_id\":\"0\",\"cookie_id\":\"ad09c90fa1a90634ad878e408411ec12c149f974\"}";
        JSONObject jsonObject = new JSONObject();
        WrapJson.wrapJson(jsonObject, Status.ParamError.getMsg(),Status.ParamError.getCode(),null);
        TRes res = new TRes();
        Circle circle = new Circle();
        try{
            JSONObject paramJson = (JSONObject) JSON.parse(param);
            circle.setCircle_r(ParamCheck.paramNotNull(Double.valueOf((String) paramJson.get("circle_r"))));
            circle.setCircle_y(ParamCheck.paramNotNull(Double.valueOf((String) paramJson.get("circle_y"))));
            circle.setCircle_x(ParamCheck.paramNotNull(Double.valueOf((String) paramJson.get("circle_x"))));
            res.setCookie_id(ParamCheck.paramNotEmptyNotNull((String) paramJson.get("cookie_id")));
            res.setCoordinate(ParamCheck.paramNotEmptyNotNull((String) paramJson.get("coordinate")));
            res.setTime_len(ParamCheck.paramNotZeroNotNull(Long.valueOf((String) paramJson.get("time_len"))));
            res.setDeviation(new GameService().circleGame(res.getCoordinate(),circle));
            res.setScore((long) Math.abs(1000-Double.parseDouble(res.getDeviation())*10));
            res.setGold((long) Double.parseDouble(res.getDeviation())/10+10);
            res.setDevice((String) paramJson.get("device"));
            res.setGroup(ParamCheck.paramIsZeroNotNull(Long.valueOf((String) paramJson.get("group"))));
            res.setCreate_time((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
            res.setReferee(checkNotNull(Long.valueOf((String) paramJson.get("referee"))));
            res.setUser_id(checkNotNull(Long.valueOf((String) paramJson.get("user_id"))));
            res.setLocation_id(Hashing.md5().newHasher().putString(res.getCreate_time()+new Random().nextLong(), Charsets.UTF_8).hash().toString());
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void qianxubianli(Node current){
        System.out.println(current.getName());
        if(current.getLeft()!=null)
            qianxubianli(current.getLeft());
        if (current.getRight()!=null)
            qianxubianli(current.getRight());
    }

    public static void zhongxubianli(Node current){
        if(current.getLeft()!=null)
            zhongxubianli(current.getLeft());
        System.out.println(current.getName());
        if (current.getRight()!=null)
            zhongxubianli(current.getRight());
    }

    public static void houxubianli(Node current){
        if(current.getLeft()!=null)
            houxubianli(current.getLeft());
        if (current.getRight()!=null)
            houxubianli(current.getRight());
        System.out.println(current.getName());
    }
}