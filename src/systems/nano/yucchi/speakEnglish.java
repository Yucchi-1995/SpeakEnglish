/*
 * speakEnglish.java
 *
 * Created on 2007/08/29, 12:39:14
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.nano.yucchi;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.jsapi.FreeTTSEngineCentral;
import java.util.Locale;
import javax.speech.EngineCreate;
import javax.speech.EngineList;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

/**
 *
 * @author Yucchi
 */
public class speakEnglish extends speakEnglish_View {

    /**
     * @param args the command line arguments
     */
    protected Synthesizer synthesizer;

    /**
     * シンセサイザを作成
     */
    public void createSynthesizer() {

        try {
            //シンセサイザモードを指定
            SynthesizerModeDesc desc = new SynthesizerModeDesc(null, "general", Locale.US, Boolean.FALSE, null);

            FreeTTSEngineCentral central = new FreeTTSEngineCentral();
            EngineList list = central.createEngineList(desc);

            if (list.size() > 0) {
                EngineCreate creator = (EngineCreate) list.get(0);
                synthesizer = (Synthesizer) creator.createEngine();
            }
            if (synthesizer == null) {
                System.err.println("シンセサイザが作れませんでした（Ｔ－Ｔ）");
                System.exit(1);
            }
            synthesizer.allocate();
            synthesizer.resume();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * テキストエリアに入力された英文を読みあげる
     */
    protected void speak(String str) {
        if (!str.isEmpty()) {
            synthesizer.speakPlainText(str, null);
        } else {
            synthesizer.speakPlainText("English has not been entered in the text area.", null);
        }
    }

    //** ここから英文読み上げ別バージョン *************************************
    // 上部のプログラムの speak(String str) メソッドをコメントアウトして無効にしてから
    // 下記コードを有効にする。
    // speakEnglish_View内のabstractメソッドspeak(String)をオーバーライドしない仕様に
    // すれば createSynthesizer() も不要となる。
//    private String voiceName = "kevin16";
//    VoiceManager voiceManager = VoiceManager.getInstance();
//    Voice voice = voiceManager.getVoice(voiceName);
//
//    protected void speak(String str) {
//        if (voice == null) {
//            System.err.println("システムエラー　ヴォイスが見つかりませんでした（T-T)");
//        }
//        if (!str.isEmpty()) {
//            voice.allocate();
//            voice.speak(str);
//        } else {
//            voice.allocate();
//            voice.speak("English has not been entered in the text area.");
//        }
//    }
    //**　ここまで別バージョン**************************************************
    /**
     * ＧＵＩを走らせる
     */
    public static void main(String[] args) {
        // TODO code application logic here
        speakEnglish_View frame2 = new speakEnglish();
        frame2.pack();
        frame2.setVisible(true);
        frame2.createSynthesizer();
    }
}
