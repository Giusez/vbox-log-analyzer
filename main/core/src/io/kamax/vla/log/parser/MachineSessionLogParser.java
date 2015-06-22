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
import io.kamax.vla.vbox.log.MachineSessionLog;

public class MachineSessionLogParser {

   private static void checkHeader(_Log rawLog) {
      if (!rawLog.getLine(1).getText().startsWith("VirtualBox VM")) {
         throw new IllegalArgumentException("Invalid log type");
      }
   }

   public static MachineSessionLog parse(_Log rawLog) {
      checkHeader(rawLog);

      return null;
   }

}
