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

import io.kamax.vla.log._Log;
import io.kamax.vla.log._LogLine;
import io.kamax.vla.log._LogParser;
import io.kamax.vla.log._LogReader;
import io.kamax.vla.vbox.LogType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VBoxLogParser implements _LogParser {

   public static final Pattern defaultTimestampPattern = Pattern.compile("^(\\d{2,}):(\\d{2}?):(\\d{2}?)\\.(\\d{6}?) (.*)");
   public static final Pattern defaultStartTimePattern = Pattern
         .compile(".*Log opened (\\d{4}?)-(\\d{2}?)-(\\d{2}?)T(\\d{2}?):(\\d{2}?):(\\d{2}?)\\.(\\d{6}?).*");

   public static long extractTimestamp(_LogLine line, long startTime, long failoverTimestamp) {
      Matcher match = defaultTimestampPattern.matcher(line.getText());
      long timeStamp = failoverTimestamp;

      if (match.matches()) {
         String hours = match.group(1);
         String mins = match.group(2);
         String sec = match.group(3);
         String microsec = match.group(4);

         timeStamp = startTime + Long.parseLong(microsec) + (Long.parseLong(sec) * 1000000L) + (Long.parseLong(mins) * 60000000L)
               + (Long.parseLong(hours) * 3600000000L);
      }

      return timeStamp;
   }

   public static long extractStartTimestamp(String line) throws ParseException {
      Matcher match = defaultStartTimePattern.matcher(line);
      if (!match.matches()) {
         throw new RuntimeException("Invalid log line");
      }

      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSSSSSSS");
      Date toTime = dateFormat.parse(match.group(1) + "-" + match.group(2) + "-" + match.group(3) + "T" + match.group(4) + ":" + match.group(5) + ":"
            + match.group(6) + "." + match.group(7));
      return toTime.getTime();
   }

   public LogType identify(_Log rawLog) {
      String header = rawLog.getLine(1).getText();

      if (header.startsWith("VirtualBox VM")) {
         return LogType.MachineSession;
      }

      return LogType.Unknown;
   }

   @Override
   public _Log parse(_LogReader reader) {
      // TODO Auto-generated method stub
      return null;
   }

}
