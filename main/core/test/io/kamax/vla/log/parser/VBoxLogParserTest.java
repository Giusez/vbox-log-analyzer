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

package io.kamax.vla.log.parser;

import static org.junit.Assert.assertTrue;
import io.kamax.vla.log.LogLine;
import java.text.ParseException;
import java.util.Date;
import org.junit.Test;


public class VBoxLogParserTest {

   @Test
   public void extractTimestamp() {
      long startTimestamp = new Date().getTime() * 1000;
      long diff = 0;

      long zeroTimeLine = VBoxLogParser.extractTimestamp(new LogLine("00:00:00.000000 We are at the beginning"), startTimestamp, startTimestamp);
      diff = zeroTimeLine - startTimestamp;
      assertTrue(Long.toString(diff), diff == 0L);

      long oneMicroSecondLine = VBoxLogParser.extractTimestamp(new LogLine("00:00:00.000001 We are at one micro second"), startTimestamp, startTimestamp);
      diff = oneMicroSecondLine - startTimestamp;
      assertTrue(Long.toString(diff), diff == 1L);

      long oneSecondLine = VBoxLogParser.extractTimestamp(new LogLine("00:00:01.000000 We are at one second"), startTimestamp, startTimestamp);
      diff = oneSecondLine - startTimestamp;
      assertTrue(Long.toString(diff), diff == 1000000L);

      long oneMinuteLine = VBoxLogParser.extractTimestamp(new LogLine("00:01:00.000000 We are at one minute"), startTimestamp, startTimestamp);
      diff = oneMinuteLine - startTimestamp;
      assertTrue(Long.toString(diff), diff == 60000000L);

      long oneHourLine = VBoxLogParser.extractTimestamp(new LogLine("01:00:00.000000 We are at one hour"), startTimestamp, startTimestamp);
      diff = oneHourLine - startTimestamp;
      assertTrue(Long.toString(diff), diff == 3600000000L);

      long randomTimeLine = VBoxLogParser.extractTimestamp(new LogLine("52:14:34.571853 We are at a first random time"), startTimestamp, startTimestamp);
      diff = randomTimeLine - startTimestamp;
      assertTrue(Long.toString(diff), diff == 188074571853L);

      long endTimeLine = VBoxLogParser.extractTimestamp(new LogLine("99999:59:59.999999 We are at the end"), startTimestamp, startTimestamp);
      diff = endTimeLine - startTimestamp;
      assertTrue(Long.toString(diff), diff == 359999999999999L);
   }

   @Test
   public void extractStartTime() throws ParseException {
      String line = "00:00:00.002000 main     Log opened 2015-06-20T22:01:11.937836400Z";
      VBoxLogParser.extractStartTimestamp(line);
   }

}
