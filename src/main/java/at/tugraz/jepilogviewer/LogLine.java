/*
 */
package at.tugraz.jepilogviewer;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lucas Reeh <lreeh@tugraz.at>
 */
@XmlRootElement
public class LogLine {

  private String line = "";

  public LogLine() {
  }

  public LogLine(String line) {
    this.line = line;
  }

  public String getLine() {
    return line;
  }

  public void setLine(String line) {
    this.line = line;
  }

}
