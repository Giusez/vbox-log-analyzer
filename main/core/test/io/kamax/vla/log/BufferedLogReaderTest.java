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

import static org.junit.Assert.assertTrue;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import org.junit.Test;


public class BufferedLogReaderTest {

   @Test
   public void readLocalVmCompleteLog() throws IOException {
      BufferedLogReader logReader = new BufferedLogReader();
      logReader.setLocation(new URL("file:ressources/test/vm-session-complete.log"));
      List<String> lines = logReader.get();
      assertTrue(Integer.toString(lines.size()), lines.size() == 1387);
   }

   @Test
   public void readLocalVmIncompleteLog() throws IOException {
      BufferedLogReader logReader = new BufferedLogReader();
      logReader.setLocation(new URL("file:ressources/test/vm-session-incomplete.log"));
      List<String> lines = logReader.get();
      assertTrue(Integer.toString(lines.size()), lines.size() == 896);
   }

   @Test
   public void readRemoteVmCompleteLog() throws IOException {
      BufferedLogReader logReader = new BufferedLogReader();
      logReader.setLocation(new URL("https://raw.githubusercontent.com/maxidor/vbox-log-analyzer/master/main/core/ressources/test/vm-session-complete.log"));
      List<String> lines = logReader.get();
      assertTrue(Integer.toString(lines.size()), lines.size() == 1387);
   }

   @Test
   public void readRemoteVmIncompleteLog() throws IOException {
      BufferedLogReader logReader = new BufferedLogReader();
      logReader.setLocation(new URL("https://raw.githubusercontent.com/maxidor/vbox-log-analyzer/master/main/core/ressources/test/vm-session-incomplete.log"));
      List<String> lines = logReader.get();
      assertTrue(Integer.toString(lines.size()), lines.size() == 896);
   }

   @Test(expected = FileNotFoundException.class)
   public void readInvalidUrl() throws IOException {
      BufferedLogReader logReader = new BufferedLogReader();
      logReader.setLocation(new URL("http://example.com/nothing.log"));
      logReader.get();
   }

}
