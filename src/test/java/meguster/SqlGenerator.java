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

		StringBuilder sb = new StringBuilder("insert into aoc.art_art(type, url) \n values \n");

		for (String embUrl : youtubeEmbLinks) {
			sb.append("('YOUTUBE', '" + embUrl + "'),\n");
		}

		for (String imgName : new File("src/main/resources/static/images").list()) {
			sb.append("('IMAGE', '" + imgName + "'),\n");
		}

		System.out.println(sb.substring(0, sb.length() - 2) + ";");
	}
}
