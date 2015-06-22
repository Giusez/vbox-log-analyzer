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

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class LogBuilder {

   private LogReader reader;
   private URL location;
   private _LogIdentifier identifier;

   public LogBuilder setFetcher(LogReader reader) {
      this.reader = reader;
      return this;
   }

   public LogBuilder setLocation(URL location) {
      this.location = location;
      return this;
   }

   public LogBuilder setIdentifier(_LogIdentifier identifier) {
      this.identifier = identifier;
      return this;
   }

   public _Log build() throws IOException {
      List<String> lines = reader.get(location);

      String parserId = identifier.identify(lines);

      _LogParser parser = LogParsers.get(parserId);

      return parser.parse(lines);
   }

}
