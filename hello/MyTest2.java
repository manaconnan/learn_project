
import com.alipay.mazexiang.learnJavaCompiler.TestInterface;
import java.util.Map;

public class MyTest2 implements TestInterface{

    public String getUdfResult(Map<String,String> map){
        String value = map.get("key2");
        String string = "hi,"+value;
        map.put("my_key_2",string);
        return string;
    }
}