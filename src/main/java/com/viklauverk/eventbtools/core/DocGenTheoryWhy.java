/*
 Copyright (C) 2021 Viklauverk AB
 Author Alexandre Hamoir

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU Affero General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Affero General Public License for more details.

 You should have received a copy of the GNU Affero General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.viklauverk.eventbtools.core;

public class DocGenTheoryWhy extends BaseDocGen {

    public DocGenTheoryWhy(CommonSettings common_settings, DocGenSettings docgen_settings, Sys sys)
    {
        super(common_settings, docgen_settings, sys, "why");
    }

    @Override
    public String suffix() {
        return ".why";
    }

    @Override
    public String generateDocument() throws Exception {
        Canvas cnvs = new Canvas();
        cnvs.setRenderTarget(RenderTarget.WHY);

        cnvs.append(Templates.Why3Definitions);
        for (int i = 0; i < 20; i++) cnvs.skipLine();
        
        for (String th : sys().theoryFullNames())
        {
            cnvs.append("EVBT(show part why \""+th+"\")\n");
        }

        return cnvs.render();
    }
    
}
