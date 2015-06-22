/*
 * VBoxLA - VirtualBox Log Analyzer
 * Copyright (C) 2015 - Maxime Dor
 * 
 * http://kamax.io/vboxla/
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * 
 */

package io.kamax.vla.log;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Log implements _Log {

   private URL source;
   private long startTimestamp;
   private List<_LogLine> lines = new ArrayList<_LogLine>();

   public Log(URL source, List<_LogLine> lines) {
      this.source = source;
      this.lines = Collections.unmodifiableList(lines);
   }

   @Override
   public URL getSource() {
      return source;
   }

   @Override
   public _LogLine getLine(int lineNumber) {
      return lines.get(lineNumber + 1);
   }

   @Override
   public int getLineCount() {
      return lines.size();
   }

   @Override
   public List<_LogLine> getLines() {
      return lines;
   }

   @Override
   public long getStartTimestamp() {
      return startTimestamp;
   }

   public void setStartTimestamp(long startTimestamp) {
      this.startTimestamp = startTimestamp;
   }

}
