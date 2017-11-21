import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cotech.model.Circle;
import com.cotech.model.TRes;
import com.cotech.util.ParamCheck;
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
//        DLiked liked = new DLiked();
//        liked.addLast(a);
//        liked.addLast(b);
//        liked.addLast(c);
//        liked.addLast(d);
//        liked.addLast(e);
//        liked.addLast(f);
//        liked.addLast(g);
//        liked.addLast(h);
//        liked.addFirst(i);
//        liked.addFirst(j);
//        liked.bianLiHeadToTail();
//        System.out.println();
//        liked.bianLiTailToHead();
//        a.setLeft(b);
//        b.setRight(c);
//        c.setLeft(d);
//        c.setRight(e);
//        a.setRight(f);
//        f.setLeft(g);
//        g.setLeft(i);
//        g.setRight(j);
//        f.setRight(h);

//        houxubianli(a);
        String param = "{\"summit\":\"337.5,200.70422535211267,77.69237886466652,650.7042253521126,597.3076211353334,650.7042253521126\",\"coordinate\":\"344,208,344,208,344,208,330.1159973144531,232.76998901367188,321.2720031738281,247.71600341796875,311.8659973144531,261.239990234375,302.2919921875,273.6260070800781,291.57598876953125,285.7279968261719,280.1960144042969,298.45001220703125,268.0580139160156,311.2760009765625,256.41400146484375,324.20599365234375,244.44601440429688,337.5039978027344,233.82400512695312,350.114013671875,223.447998046875,361.2200012207031,213.73800659179688,372.2040100097656,205.64599609375,381.635986328125,197.61399841308594,391.6259765625,190.30599975585938,402.260009765625,182.30999755859375,413.5360107421875,174.33999633789062,426.76800537109375,166.3280029296875,440.1199951171875,158.31199645996094,452.81201171875,150.46200561523438,465.22802734375,142.32000732421875,478.802001953125,134.29600524902344,491.5579833984375,127.58000183105469,503.5560302734375,122.20199584960938,516.8300170898438,116.87600708007812,529.530029296875,112.80799865722656,541.5780029296875,110.10200500488281,551.64599609375,107.42799377441406,561.6719970703125,105.40799713134766,570.81201171875,102.81800079345703,578.8759765625,100.73999786376953,586.8900146484375,98.77200317382812,593.1240234375,96.08200073242188,599.7919921875,93.43000030517578,606.4240112304688,90.10199737548828,613.6939697265625,86.13600158691406,620.4400024414062,82.80999755859375,627.093994140625,80.14600372314453,633.0419921875,77.43399810791016,638.4639892578125,74.7760009765625,644.447998046875,72.0979995727539,651.0880126953125,69.43000030517578,657.1380004882812,68.05400085449219,661.8400268554688,67.33399963378906,665.2360229492188,67.33399963378906,667.2760009765625,69.20800018310547,668.60400390625,76.26200103759766,669.9420166015625,89.3479995727539,669.3920288085938,104.64199829101562,666.1740112304688,120.83799743652344,662.1240234375,136.0399932861328,658.156005859375,152.63999938964844,654.1599731445312,169.9720001220703,650.8259887695312,188.01199340820312,648.0999755859375,205.64599609375,645.489990234375,223.1999969482422,643.4559936523438,240.19601440429688,641.5079956054688,257.6839904785156,638.8179931640625,275.364013671875,636.0980224609375,292.75799560546875,634.0440063476562,308.1940002441406,632.7100219726562,322.8160095214844,631.3800048828125,337.46600341796875,630.0479736328125,352.7919921875,628.7160034179688,366,628,379.4779968261719,627.333984375,392.2080078125,626.0460205078125,403.9159851074219,624.75,417.3819885253906,623.39599609375,430.2120056152344,622.0460205078125,442.2619934082031,620.7119750976562,454,620,464,620,474,620,484.385986328125,621.2860107421875,492.35797119140625,622.6160278320312,500.31201171875,625.2139892578125,508.27001953125,627.8679809570312,516.39599609375,630.5759887695312,524.4340209960938,633.2559814453125,532.4140014648438,635.916015625,540.3939819335938,637.2880249023438,548.3359985351562,638.6119995117188,555.7680053710938,639.9619750976562,562.3880004882812,641.2780151367188,567.823974609375,642.6220092773438,573.1179809570312,643.9459838867188,577.1599731445312,644.666015625,581.2059936523438,645.2899780273438,585.2000122070312,645.9559936523438,588,646,590,646,593.9140014648438,646.666015625,595.9559936523438,646.666015625,597.2880249023438,646.666015625,598.333984375,646.666015625,598,644,598,640,596.72998046875,635.0499877929688,593.5360107421875,624.5399780273438,588.176025390625,612.39599609375,578.9340209960938,596.4359741210938,567.0399780273438,575.3319702148438,553.0540161132812,551.3359985351562,538.3079833984375,526.5040283203125,523.72998046875,502.01800537109375,509.70001220703125,479.91998291015625,495.052001953125,459.8480224609375,479.1059875488281,438.58599853515625,463.7539978027344,417.89398193359375,449.0039978027344,396.489990234375,437.6180114746094,378.4639892578125,426.9179992675781,361.0740051269531,417.52801513671875,345,410.1700134277344,331.6759948730469,403.5,317.0660095214844,396.8059997558594,302.3059997558594,390.7879943847656,289.6080017089844,385.49200439453125,280.27801513671875,381.37200927734375,270.75799560546875,377.4219970703125,260.906005859375,374.7720031738281,251.0360107421875,373.3659973144531,241.5679931640625,372.0400085449219,233.57199096679688,371.3340148925781,226.9119873046875,371.3340148925781,222.11599731445312\",\"time_len\":\"2239\",\"device\":\"Mozilla/5.0 (Linux; Android 7.0; MI 5s Build/NRD90M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/53.0.2785.49 Mobile MQQBrowser/6.2 TBS/043622 Safari/537.36 V1_AND_SQ_7.2.5_744_YYB_D QQ/7.2.5.3305 NetType/WIFI WebP/0.3.0 Pixel/1080\",\"referee\":\"0\",\"user_id\":\"11\",\"group\":\"0\",\"cookie_id\":\"3181b4b53f2a9ea6ec6a227bb8d2827f97ae8929\",\"deviation\":\"0.64521\",\"score\":\"645\"}\n";
        JSONObject paramJson = (JSONObject) JSON.parse(param);
        TRes res = new TRes();
        Circle circle = new Circle();
        try {
            if(paramJson.containsKey("summit")){
                res.setAddress("triangle:"+paramJson.getString("summit"));
            }else if (paramJson.containsKey("circle_r")){
                circle.setCircle_r(ParamCheck.paramNotNull(Double.valueOf((String) paramJson.get("circle_r"))));
                circle.setCircle_y(ParamCheck.paramNotNull(Double.valueOf((String) paramJson.get("circle_y"))));
                circle.setCircle_x(ParamCheck.paramNotNull(Double.valueOf((String) paramJson.get("circle_x"))));
                res.setAddress(circle.toString());
            }
            res.setCookie_id(ParamCheck.paramNotEmptyNotNull((String) paramJson.get("cookie_id")));
            res.setCoordinate(ParamCheck.paramNotEmptyNotNull((String) paramJson.get("coordinate")));
            res.setTime_len(ParamCheck.paramNotZeroNotNull(Long.valueOf((String) paramJson.get("time_len"))));
            res.setDeviation(ParamCheck.paramNotEmptyNotNull(paramJson.getString("deviation")));
            res.setScore(ParamCheck.paramGreatThanZeroNotNull(paramJson.getLong("score")));
            res.setGold(10 + res.getScore() / 100);
            res.setDevice((String) paramJson.get("device"));
            res.setGroup(ParamCheck.paramNotEmptyNotNull((String) paramJson.get("group")));
            res.setCreate_time((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
            res.setReferee(checkNotNull(Long.valueOf((String) paramJson.get("referee"))));
            res.setUser_id(checkNotNull(Long.valueOf((String) paramJson.get("user_id"))));
            res.setLocation_id(Hashing.md5().newHasher().putString(res.getCreate_time() + new Random().nextLong(), Charsets.UTF_8).hash().toString());
        }catch (Exception e){
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