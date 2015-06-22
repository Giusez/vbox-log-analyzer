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
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.Test;

public class LogBuilderTest {

   @Test
   public void tryCompleteLog() throws MalformedURLException, IOException {
      URL location = new File("ressources/vm-session-complete.log").toURI().toURL();
      LogReader reader = new LogReader();
      _LogIdentifier ider = new LogIdentifier();

      LogBuilder builder = new LogBuilder();
      builder.setLocation(location);
      builder.setFetcher(reader);
      builder.setIdentifier(ider);

      _Log log = builder.build();

      assertTrue(Integer.toString(log.getLineCount()), log.getLineCount() == 1387);
      assertTrue(log.getLine(1387).getText(), log.getLine(1387).getText().contains("00:37:02.259922"));
   }

   @Test
   public void tryIncompleteLog() throws MalformedURLException, IOException {
      URL location = new File("ressources/vm-session-incomplete.log").toURI().toURL();
      LogReader reader = new LogReader();
      _LogIdentifier ider = new LogIdentifier();

      LogBuilder builder = new LogBuilder();
      builder.setLocation(location);
      builder.setFetcher(reader);
      builder.setIdentifier(ider);

      _Log log = builder.build();

      assertTrue(Integer.toString(log.getLineCount()), log.getLineCount() == 896);
      assertTrue(log.getLine(896).getText(), log.getLine(896).getText().contains("00:11:48.337014"));
   }

}
