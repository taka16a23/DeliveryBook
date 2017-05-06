package info.no_ip.taka16.deliverybook;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import info.no_ip.taka16.deliverybook.book.Book;
import info.no_ip.taka16.deliverybook.book.BookRepository;
import info.no_ip.taka16.deliverybook.frame.Frame;
import info.no_ip.taka16.deliverybook.frame.FrameRepository;



public class DebugDataMakerActivity extends Activity implements View.OnClickListener{
    ArrayList<List<String>> sampleData = new ArrayList<List<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug_data_maker);

        ((Button)findViewById(R.id.debug_1_area)).setOnClickListener(this);
        ((Button)findViewById(R.id.debug_2_area)).setOnClickListener(this);
        ((Button)findViewById(R.id.debug_3_area)).setOnClickListener(this);

        sampleData.add(Arrays.asList("滋賀 太郎", "滋賀県大津市御陵町3番1号", "朝日", "入れ忘れ注意"));
        sampleData.add(Arrays.asList("上杉 景勝", "滋賀県大津市御陵町3番1号", "朝日", "雨濡れ注意"));
        sampleData.add(Arrays.asList("上杉 謙信", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("与謝野 晶子", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("平賀 源内", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("乃木 希典", "滋賀県大津市御陵町3番1号", "朝日 日刊ス", "入れ忘れ注意"));
        sampleData.add(Arrays.asList("久坂 玄瑞", "滋賀県大津市御陵町3番1号", "朝日 日刊ス", ""));
        sampleData.add(Arrays.asList("井伊 直弼", "滋賀県大津市御陵町3番1号", "毎日", ""));
        sampleData.add(Arrays.asList("今川 義元", "滋賀県大津市御陵町3番1号", "産経", "公園手前"));
        sampleData.add(Arrays.asList("野口 英世", "滋賀県大津市御陵町3番1号", "毎日", ""));
        sampleData.add(Arrays.asList("伊能 忠敬", "滋賀県大津市御陵町3番1号", "朝日 土日日刊ス", ""));
        sampleData.add(Arrays.asList("伊藤 博文", "滋賀県大津市御陵町3番1号", "朝日", "アメP"));
        sampleData.add(Arrays.asList("伊達 政宗", "滋賀県大津市御陵町3番1号", "産経", "雨濡れ注意"));
        sampleData.add(Arrays.asList("前田 利家", "滋賀県大津市御陵町3番1号", "朝日 日刊ス", ""));
        sampleData.add(Arrays.asList("加藤 清正", "滋賀県大津市御陵町3番1号", "毎日", ""));
        sampleData.add(Arrays.asList("勝 海舟", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("北条 政子", "滋賀県大津市御陵町3番1号", "朝日", "アメP"));
        sampleData.add(Arrays.asList("北条 氏康", "滋賀県大津市御陵町3番1号", "朝日 土日日刊ス", ""));
        sampleData.add(Arrays.asList("千 利休", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("新田 義貞", "滋賀県大津市御陵町3番1号", "朝日", "塀P"));
        sampleData.add(Arrays.asList("吉田 松陰", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("吉田 茂", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("土方 歳三", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("坂本 龍馬", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("夏目 漱石", "滋賀県大津市御陵町3番1号", "毎日 日刊ス", ""));
        sampleData.add(Arrays.asList("大久保 利通", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("大友 宗麟", "滋賀県大津市御陵町3番1号", "朝日", "入れ忘れ注意"));
        sampleData.add(Arrays.asList("大村 益次郎", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("大隈 重信", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("天草 四郎", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("宇喜多 秀家", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("宮沢 賢治", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("小早川 秀秋", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("小早川 隆景", "滋賀県大津市御陵町3番1号", "朝日 土日日刊ス", ""));
        sampleData.add(Arrays.asList("山本 五十六", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("山県 有朋", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("岩倉 具視", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("岩崎 弥太郎", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("島津 義弘", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("平 将門", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("平 清盛", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("平 賀源内", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("直江 兼続", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("徳川 吉宗", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("徳川 家光", "滋賀県大津市御陵町3番1号", "朝日 土日日刊ス", ""));
        sampleData.add(Arrays.asList("徳川 家康", "滋賀県大津市御陵町3番1号", "朝日", "雨濡れ注意"));
        sampleData.add(Arrays.asList("徳川 慶喜", "滋賀県大津市御陵町3番1号", "毎日 日刊ス", ""));
        sampleData.add(Arrays.asList("徳川 綱吉", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("徳川 秀忠", "滋賀県大津市御陵町3番1号", "産経", ""));
        sampleData.add(Arrays.asList("斎藤 一", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("斎藤 道三", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("新渡戸 稲造", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("新田 義貞", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("明智 光秀", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("春日 局", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("最上 義光", "滋賀県大津市御陵町3番1号", "朝日", "入れ忘れ注意"));
        sampleData.add(Arrays.asList("木戸 孝允", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("本多 忠勝", "滋賀県大津市御陵町3番1号", "毎日", ""));
        sampleData.add(Arrays.asList("東条 英機", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("東郷 平八郎", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("松尾 芭蕉", "滋賀県大津市御陵町3番1号", "朝日 土日日刊ス", ""));
        sampleData.add(Arrays.asList("松平 定信", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("板垣 退助", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("柴田 勝家", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("森 可成", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("森 鴎外", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("楠木 正成", "滋賀県大津市御陵町3番1号", "朝日", "入れ忘れ注意"));
        sampleData.add(Arrays.asList("樋口 一葉", "滋賀県大津市御陵町3番1号", "朝日 日刊ス", ""));
        sampleData.add(Arrays.asList("正岡 子規", "滋賀県大津市御陵町3番1号", "産経", ""));
        sampleData.add(Arrays.asList("武市 半平太", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("武田 信玄", "滋賀県大津市御陵町3番1号", "朝日", "雨濡れ注意"));
        sampleData.add(Arrays.asList("武蔵坊 弁慶", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("毛利 元就", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("沖田 総司", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("浅井 長政", "滋賀県大津市御陵町3番1号", "毎日", ""));
        sampleData.add(Arrays.asList("清少納言", "滋賀県大津市御陵町3番1号", "朝日 土日日刊ス", ""));
        sampleData.add(Arrays.asList("渋沢 栄一", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("源 義経", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("源 頼朝", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("犬養 毅", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("滝川 一益", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("田沼 意次", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("直江 兼続", "滋賀県大津市御陵町3番1号", "朝日", "入れ忘れ注意"));
        sampleData.add(Arrays.asList("真田 幸村", "滋賀県大津市御陵町3番1号", "毎日", ""));
        sampleData.add(Arrays.asList("石原 莞爾", "滋賀県大津市御陵町3番1号", "朝日 日刊ス", ""));
        sampleData.add(Arrays.asList("石田 三成", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("福島 正則", "滋賀県大津市御陵町3番1号", "朝日", "雨濡れ注意"));
        sampleData.add(Arrays.asList("福沢 諭吉", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("秋山 真之", "滋賀県大津市御陵町3番1号", "産経", ""));
        sampleData.add(Arrays.asList("紫式部", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("細川 忠興", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("織田 信長", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("芥川 龍之介", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("芹沢 鴨", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("菅原 道真", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("葛飾 北斎", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("藤原 道長", "滋賀県大津市御陵町3番1号", "朝日", "入れ忘れ注意"));
        sampleData.add(Arrays.asList("藤堂 高虎", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("西郷 隆盛", "滋賀県大津市御陵町3番1号", "朝日 土日日刊ス", ""));
        sampleData.add(Arrays.asList("豊臣秀吉", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("豊臣 秀頼", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("足利 尊氏", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("足利 義満", "滋賀県大津市御陵町3番1号", "朝日", "雨濡れ注意"));
        sampleData.add(Arrays.asList("近藤 勇", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("近衛 文麿", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("野口 英世", "滋賀県大津市御陵町3番1号", "朝日 日刊ス", ""));
        sampleData.add(Arrays.asList("鈴木 貫太郎", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("長宗我部 元親", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("陸奥 宗光", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("高杉 晋作", "滋賀県大津市御陵町3番1号", "朝日 土日日刊ス", ""));
        sampleData.add(Arrays.asList("高橋 是清", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("黒田 官兵衛", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("黒田 長政", "滋賀県大津市御陵町3番1号", "毎日", ""));
        sampleData.add(Arrays.asList("織田 信忠", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("蘇我 入鹿", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("蘇我 馬子", "滋賀県大津市御陵町3番1号", "朝日", "入れ忘れ注意"));
        sampleData.add(Arrays.asList("和気 清麻呂", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("六角 義賢", "滋賀県大津市御陵町3番1号", "朝日", "入れ忘れ注意"));
        sampleData.add(Arrays.asList("松平 定信", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("前田 利家", "滋賀県大津市御陵町3番1号", "毎日", ""));
        sampleData.add(Arrays.asList("本多 忠勝", "滋賀県大津市御陵町3番1号", "朝日", ""));
        sampleData.add(Arrays.asList("北条 氏政", "滋賀県大津市御陵町3番1号", "朝日", ""));
    }

    private void createData(String areaName){
        Book book = new BookRepository(this).getBook(areaName);
        FrameRepository frameRepository = new FrameRepository(this);
        for (List<String> array : sampleData) {
            Frame frame = new Frame();
            frame.setName(array.get(0));
            frame.setAddress(array.get(1));
            frame.setDeliverable(array.get(2));
            frame.setDescription(array.get(3));
            frameRepository.register(frame);
            book.insert(frame);
        }
        Toast.makeText(this, areaName + " data created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.debug_1_area:
                Toast.makeText(this, "debug1", Toast.LENGTH_SHORT).show();
                createData("1区");
                break;
            case R.id.debug_2_area:
                createData("2区");
                break;
            case R.id.debug_3_area:
                createData("3区");
                break;
        }
    }

}
