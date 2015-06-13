package meguster;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import com.google.common.io.Resources;

/**
 * Just a helper class which generates SQL to init data in our DB.
 * 
 * @author Kris Barczynski
 */
public class SqlGenerator {

	public static void main(String[] args) throws IOException {
		URL url = Resources.getResource("youtube-links.txt");
		List<String> youtubeEmbLinks = Resources.readLines(url, Charset.defaultCharset());

		StringBuilder sb = new StringBuilder("insert into art(id, type, url) \n values \n");

		for (String embUrl : youtubeEmbLinks) {
			sb.append("(nextval('art_id_seq'::regclass), 1, '" + embUrl + "'),\n");
		}

		for (String imgName : new File("src/main/resources/static/business-img").list()) {
			sb.append("(nextval('art_id_seq'::regclass), 0, '" + imgName + "'),\n");
		}

		System.out.println(sb.substring(0, sb.length() - 2) + ";");
		
//		System.out.println("DELETE FROM aoc.art_art WHERE art_id in ( ");
//		System.out.println("select art1.art_id from aoc.art_art art1, aoc.art_art art2 where art1.art_id > art2.art_id and art1.type = art2.type and art1.url = art2.url ");
//		System.out.println(" );");
		
	}
}
