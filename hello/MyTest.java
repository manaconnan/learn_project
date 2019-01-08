
import com.alipay.mazexiang.learnJavaCompiler.TestInterface;
import java.util.Map;

public class MyTest implements TestInterface{

    public String getUdfResult(Map<String,String> map){
        String value = map.get("key1");
        String string = "hello,"+value;
        map.put("my_key",string);
        return string;
    }
}