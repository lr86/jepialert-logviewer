package at.tugraz.jepilogviewer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Simple mock based service implementation.
 * <p>
 * @author Christian Sadilek <csadilek@redhat.com>
 */
@Path("/rest")
public class LogViewerService {

  @GET
  @Path("/log")
  public List<LogLine> log() throws IOException {
    List<LogLine> lines = new ArrayList<LogLine>();

    FileInputStream fileInputStream = new FileInputStream(new File("/home/pi/jepialert.log"));
    FileChannel channel = fileInputStream.getChannel();
    ByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
    buffer.position((int) channel.size());
    int count = 0;

    StringBuilder builder = new StringBuilder();
    for (long i = channel.size() - 1; i >= 0; i--) {
      char c = (char) buffer.get((int) i);
      builder.append(c);
      if (c == '\n') {
        if (count == 100) {
          break;
        }
        count++;
        builder.reverse();
        lines.add(new LogLine(builder.toString()));
        builder = null;
        builder = new StringBuilder();
      }
    }
    channel.close();
    return lines;
  }

}
