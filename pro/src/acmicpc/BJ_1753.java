package acmicpc;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class BJ_1753 {

    // INF 媛� 珥덇린�솕
    static final int INF = 9999999;
    // 洹몃옒�봽瑜� �몴�쁽 �븷 2李⑥썝 List
    static List<List<Node>> graph = new ArrayList<>();
    // 理쒕떒嫄곕━�뀒�씠釉붿쓣 �몴�쁽 �븷 諛곗뿴
    static int[] result;
    // 諛⑸Ц泥섎━瑜� �쐞�븳 諛곗뿴�씠吏�留� ���뒗 �떎瑜� 諛⑸쾿�쑝濡� 諛⑸Ц泥섎━瑜� 吏꾪뻾�븯寃좎뒿�땲�떎.
    //	static boolean[] vistied;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] info = br.readLine().split(" ");
        int startIndex = Integer.parseInt(br.readLine());

        // 洹몃옒�봽 �깮�꽦
        for (int i = 0; i < Integer.parseInt(info[0])+1; i++) {
            graph.add(new ArrayList<>());
        }
        // 理쒕떒嫄곕━�뀒�씠釉� �깮�꽦
        result = new int[Integer.parseInt(info[0])+1];
        // 理쒕떒嫄곕━�뀒�씠釉� INF濡� 珥덇린�솕
        Arrays.fill(result, INF);

        // 諛⑸Ц泥섎━瑜� �쐞�븳 諛곗뿴 �깮�꽦 (���뒗 �궗�슜�븯吏� �븡�뒿�땲�떎)
        // vistied = new boolean[Integer.parseInt(info[0])+1];

        // 臾몄젣�뿉�꽌 二쇱뼱吏� �엯�젰 媛믪뿉 �뵲�씪 洹몃옒�봽 珥덇린�솕
        for (int i = 0; i < Integer.parseInt(info[1]); i++) {
            String[] temp = br.readLine().split(" ");
            graph.get(Integer.parseInt(temp[0])).add(new Node(Integer.parseInt(temp[1]), Integer.parseInt(temp[2])));
        }

        // 臾몄젣�뿉�꽌 二쇱뼱吏� �엯�젰媛믪쓣 諛뷀깢�쑝濡� �떎�씡�뒪�듃�씪 �븣怨좊━利� �닔�뻾
        dijkstra(startIndex);

        // 臾몄젣�뿉�꽌 �젣�떆�븳 議곌굔�뿉 留욊쾶 異쒕젰
        for (int i = 1; i < result.length; i++) {
            if(result[i] == INF) {
                bw.write("INF");
                bw.newLine();
            }else {
                bw.write(String.valueOf(result[i]));
                bw.newLine();
            }
        }
        bw.flush();

    }

    // �떎�씡�뒪�듃�씪 �븣怨좊━利�
    static void dijkstra(int index) {

        // 理쒕떒嫄곕━媛� 媛깆떊 �맂 �끂�뱶�뱾�쓣 �떞�쓣 �슦�꽑�닚�쐞 �걧 �깮�꽦
        PriorityQueue<Node> pq =  new PriorityQueue<>();
        // 理쒕떒嫄곕━�뀒�씠釉붿쓽 �떆�옉吏��젏�끂�뱶 媛� 0�쑝濡� 媛깆떊
        result[index] = 0;
        // �슦�꽑�닚�쐞 �걧�뿉 �떆�옉�끂�뱶 �꽔湲�
        pq.offer(new Node(index, 0));

        // �슦�꽑�닚�쐞 �걧�뿉 �끂�뱶媛� 議댁옱�븯硫� 怨꾩냽 諛섎났
        while(!pq.isEmpty()) {
            // �걧�뿉�꽌 �끂�뱶 爰쇰궡湲�
            Node node = pq.poll();
            // 爰쇰궦 �끂�뱶�쓽 �씤�뜳�뒪 諛� 理쒕떒嫄곕━ 鍮꾩슜 �솗�씤
            int nodeIndex = node.index;
            int distance = node.distacne;

            // �븵�뿉�꽌 二쇱꽍泥섎━�뻽�뜕 諛⑸Ц泥섎━ 諛곗뿴�쓣 �궗�슜�빐�꽌 �븘�옒�� 媛숈씠 諛⑸Ц泥섎━�븯�뀛�룄 �맗�땲�떎.
//			if(vistied[nodeIndex]) {
//				continue;
//			}else{
//				vistied[nodeIndex] = true;
//			}

            // �걧�뿉�꽌 爰쇰궦 嫄곕━�� 理쒕떒嫄곕━�뀒�씠釉붿쓽 媛믪쓣 鍮꾧탳�빐�꽌 諛⑸Ц泥섎━瑜� �빀�땲�떎.
            // �걧�뒗 理쒕떒嫄곕━瑜� 湲곗��쑝濡� �삤由꾩감�닚 �젙�젹�릺怨� �엳�뒿�땲�떎.
            // 留뚯빟 �쁽�옱 爰쇰궦 �끂�뱶�쓽 嫄곕━媛� 理쒕떒嫄곕━�뀒�씠釉붿쓽 媛믩낫�떎 �겕�떎硫� �빐�떦 �끂�뱶�뒗 �씠�쟾�뿉 諛⑸Ц�맂 �끂�뱶�엯�땲�떎.
            // 洹몃윭誘�濡� �빐�떦�끂�뱶�� �뿰寃� �맂 �끂�뱶瑜� �깘�깋�븯吏� �븡怨� �걧�뿉�꽌 �떎�쓬 �끂�뱶瑜� 爰쇰깄�땲�떎.
            if(distance > result[nodeIndex]) {
                continue;
            }

            // �걧�뿉�꽌 爰쇰궦 �끂�뱶�뿉�꽌 �씠�룞 媛��뒫 �븳 �끂�뱶�뱾�쓣 �깘�깋�빀�땲�떎.
            for (Node linkedNode : graph.get(nodeIndex)) {
                // �빐�떦�끂�뱶瑜� 嫄곗퀜�꽌 �떎�쓬 �끂�뱶濡� �씠�룞 �븷 �뻹�쓽 媛믪씠 �떎�쓬 �씠�룞�끂�뱶�쓽 理쒕떒嫄곕━�뀒�씠釉� 媛믩낫�떎 �옉�쓣 �븣
                if(distance + linkedNode.distacne < result[linkedNode.index]) {
                    // if臾몄쓽 議곌굔�쓣 留뚯”�뻽�떎硫� 理쒕떒嫄곕━�뀒�씠釉붿쓽 媛믪쓣 媛깆떊�빀�땲�떎.
                    result[linkedNode.index] = distance + linkedNode.distacne;
                    // 媛깆떊 �맂 �끂�뱶瑜� �슦�꽑�닚�쐞 �걧�뿉 �꽔�뼱以띾땲�떎.
                    pq.offer(new Node(linkedNode.index, result[linkedNode.index]));
                }
            }
        }
    }

    // �슦�꽑�닚�쐞 �걧�뿉�꽌 �젙�젹湲곗��쓣 �옟湲곗쐞�빐 Comparable瑜� 援ы쁽�빀�땲�떎.
    static class Node implements Comparable<Node>{
        int index;			// �끂�뱶 踰덊샇
        int distacne;		// �씠�룞 �븷 �끂�뱶源뚯��쓽 嫄곕━

        Node(int index, int distacne) {
            this.index = index;
            this.distacne = distacne;
        }

        // 理쒕떒嫄곕━瑜� 湲곗��쑝濡� �삤由꾩감�닚 �젙�젹�빀�땲�떎.
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.distacne, o.distacne);
        }
    }

}