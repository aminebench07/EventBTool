// Copyright Viklauverk AB 2021
// Generated by "make templates"
package com.viklauverk.eventbtools.core;
public class Templates {
public static String empty = "";
public static String TeXHeader =
"\\documentclass[10pt,a4paper]{article}\n"+
"\\usepackage[T1]{fontenc}    % Ignored when using xelatex\n"+
"\\usepackage[utf8]{inputenc} % Ignored when using xelatex\n"+
"\\usepackage{bsymb}\n"+
"\\usepackage{calc}\n"+
"\\usepackage{parskip}\n"+
"\\usepackage{graphicx}\n"+
"\\graphicspath{ {$PATH_TO_IMAGES$} }\n"+
"\\usepackage{xltabular}\n"+
"\\usepackage{xcolor}\n"+
"\\usepackage{tikz}\n"+
"\\usepackage{makeidx}\n"+
"\\usepackage[margin=3cm]{geometry}\n"+
"\\usepackage[explicit]{titlesec}\n"+
"\\usepackage{varwidth} % for the varwidth minipage environment\n"+
"\\usepackage{adjustbox}\n"+
"\n"+
"%\\usepackage{blindtext}\n"+
"%\\usepackage{showframe}\n"+
"\n"+
"\\titleformat{\\section}[rightmargin]{}{\\fbox{\\thesection}}{0pt}{}[]\n"+
"\\titlespacing{\\section}{10mm}{*0.5}{*0.5}\n"+
"\\titleformat{\\subsection}[rightmargin]{}{\\fbox{\\thesubsection}}{0pt}{}[]\n"+
"\\titlespacing{\\subsection}{10mm}{*0.5}{*0.5}\n"+
"\\titleformat{\\subsubsection}[rightmargin]{}{\\fbox{\\thesubsubsection}}{0pt}{}[]\n"+
"\\titlespacing{\\subsubsection}{10mm}{*0.5}{*0.5}\n"+
"";
public static String TeXPackages =
"\\usepackage[T1]{fontenc}    % Ignored when using xelatex\n"+
"\\usepackage[utf8]{inputenc} % Ignored when using xelatex\n"+
"\\usepackage{bsymb} % Standard latex symbols for Event-B formulas.\n"+
"\\usepackage{calc}  % Improve latex calculation abilities.\n"+
"\\usepackage{parskip} % Use parskip, instead of line-indent for new paragraphs.\n"+
"\\usepackage{graphicx} % Include graphics.\n"+
"\\graphicspath{ {$PATH_TO_IMAGES$} } % Points to workspace directory to find images.\n"+
"\\usepackage{xltabular} % Advanced tabular command.\n"+
"\\usepackage{tikz} % Draw icons for (a) (i) (r) (u)\n"+
"\\usepackage{xcolor} % Specify colors\n"+
"\\usepackage{makeidx} % Index generation\n"+
"\\usepackage[margin=3cm]{geometry} % Set margins.\n"+
"\\usepackage[explicit]{titlesec} % Used to tweak table of contents and sections.\n"+
"\\usepackage{varwidth} % For the varwidth minipage environment.\n"+
"\\usepackage{adjustbox} % Minimum sized box.\n"+
"\n"+
"%\\usepackage{blindtext} % Use this to debug latex.\n"+
"%\\usepackage{showframe} % Use this to debug latex.\n"+
"";
public static String HtmqHeader =
"html {\n"+
"    header {\n"+
"        meta(http-equiv=content-type content='text/html;charset=utf-8')\n"+
"        title=$TITLE$\n"+
"        script(src='https://polyfill.io/v3/polyfill.min.js?features=es6')\n"+
"        script(id=MathJax-script async src=https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js)\n"+
"        link(rel=preconnect href=https://fonts.gstatic.com)\n"+
"        link(href='https://fonts.googleapis.com/css2?family=EB+Garamond&display=swap' rel=stylesheet)\n"+
"        link(type=text/css href=$STYLE$ rel=stylesheet)\n"+
"    }\n"+
"    body {\n"+
"";
public static String TeXDefinitions =
"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n"+
"%%\n"+
"%% TeXDefinitions for EVBT\n"+
"%% Requires that package bsymb,calc,array and varwidth are loaded.\n"+
"%%\n"+
"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n"+
"\\overfullrule=10pt\n"+
"\\newcommand{\\EVBTcolor}[1]{\\color{#1}}\n"+
"\n"+
"%% The X11 colors.\n"+
"\\newcommand{\\AliceBlue}{F0F8FF}\n"+
"\\newcommand{\\AntiqueWhite}{FAEBD7}\n"+
"\\newcommand{\\Aqua}{00FFFF}\n"+
"\\newcommand{\\Aquamarine}{7FFFD4}\n"+
"\\newcommand{\\Azure}{F0FFFF}\n"+
"\\newcommand{\\Beige}{F5F5DC}\n"+
"\\newcommand{\\Bisque}{FFE4C4}\n"+
"\\newcommand{\\Black}{000000}\n"+
"\\newcommand{\\BlanchedAlmond}{FFEBCD}\n"+
"\\newcommand{\\Blue}{0000FF}\n"+
"\\newcommand{\\BlueViolet}{8A2BE2}\n"+
"\\newcommand{\\Brown}{A52A2A}\n"+
"\\newcommand{\\Burlywood}{DEB887}\n"+
"\\newcommand{\\CadetBlue}{5F9EA0}\n"+
"\\newcommand{\\Chartreuse}{7FFF00}\n"+
"\\newcommand{\\Chocolate}{D2691E}\n"+
"\\newcommand{\\Coral}{FF7F50}\n"+
"\\newcommand{\\CornflowerBlue}{6495ED}\n"+
"\\newcommand{\\Cornsilk}{FFF8DC}\n"+
"\\newcommand{\\Crimson}{DC143C}\n"+
"\\newcommand{\\Cyan}{00FFFF}\n"+
"\\newcommand{\\DarkBlue}{00008B}\n"+
"\\newcommand{\\DarkCyan}{008B8B}\n"+
"\\newcommand{\\DarkGoldenrod}{B8860B}\n"+
"\\newcommand{\\DarkGray}{A9A9A9}\n"+
"\\newcommand{\\DarkGreen}{006400}\n"+
"\\newcommand{\\DarkKhaki}{BDB76B}\n"+
"\\newcommand{\\DarkMagenta}{8B008B}\n"+
"\\newcommand{\\DarkOliveGreen}{556B2F}\n"+
"\\newcommand{\\DarkOrange}{FF8C00}\n"+
"\\newcommand{\\DarkOrchid}{9932CC}\n"+
"\\newcommand{\\DarkRed}{8B0000}\n"+
"\\newcommand{\\DarkSalmon}{E9967A}\n"+
"\\newcommand{\\DarkSeaGreen}{8FBC8F}\n"+
"\\newcommand{\\DarkSlateBlue}{483D8B}\n"+
"\\newcommand{\\DarkSlateGray}{2F4F4F}\n"+
"\\newcommand{\\DarkTurquoise}{00CED1}\n"+
"\\newcommand{\\DarkViolet}{9400D3}\n"+
"\\newcommand{\\DeepPink}{FF1493}\n"+
"\\newcommand{\\DeepSkyBlue}{00BFFF}\n"+
"\\newcommand{\\DimGray}{696969}\n"+
"\\newcommand{\\DodgerBlue}{1E90FF}\n"+
"\\newcommand{\\Firebrick}{B22222}\n"+
"\\newcommand{\\FloralWhite}{FFFAF0}\n"+
"\\newcommand{\\ForestGreen}{228B22}\n"+
"\\newcommand{\\Fuchsia}{FF00FF}\n"+
"\\newcommand{\\Gainsboro}{DCDCDC}\n"+
"\\newcommand{\\GhostWhite}{F8F8FF}\n"+
"\\newcommand{\\Gold}{FFD700}\n"+
"\\newcommand{\\Goldenrod}{DAA520}\n"+
"\\newcommand{\\Gray}{BEBEBE}\n"+
"\\newcommand{\\WebGray}{808080}\n"+
"\\newcommand{\\Green}{00FF00}\n"+
"\\newcommand{\\WebGreen}{008000}\n"+
"\\newcommand{\\GreenYellow}{ADFF2F}\n"+
"\\newcommand{\\Honeydew}{F0FFF0}\n"+
"\\newcommand{\\HotPink}{FF69B4}\n"+
"\\newcommand{\\IndianRed}{CD5C5C}\n"+
"\\newcommand{\\Indigo}{4B0082}\n"+
"\\newcommand{\\Ivory}{FFFFF0}\n"+
"\\newcommand{\\Khaki}{F0E68C}\n"+
"\\newcommand{\\Lavender}{E6E6FA}\n"+
"\\newcommand{\\LavenderBlush}{FFF0F5}\n"+
"\\newcommand{\\LawnGreen}{7CFC00}\n"+
"\\newcommand{\\LemonChiffon}{FFFACD}\n"+
"\\newcommand{\\LightBlue}{ADD8E6}\n"+
"\\newcommand{\\LightCoral}{F08080}\n"+
"\\newcommand{\\LightCyan}{E0FFFF}\n"+
"\\newcommand{\\LightGoldenrod}{FAFAD2}\n"+
"\\newcommand{\\LightGray}{D3D3D3}\n"+
"\\newcommand{\\LightGreen}{90EE90}\n"+
"\\newcommand{\\LightPink}{FFB6C1}\n"+
"\\newcommand{\\LightSalmon}{FFA07A}\n"+
"\\newcommand{\\LightSeaGreen}{20B2AA}\n"+
"\\newcommand{\\LightSkyBlue}{87CEFA}\n"+
"\\newcommand{\\LightSlateGray}{778899}\n"+
"\\newcommand{\\LightSteelBlue}{B0C4DE}\n"+
"\\newcommand{\\LightYellow}{FFFFE0}\n"+
"\\newcommand{\\Lime}{00FF00}\n"+
"\\newcommand{\\LimeGreen}{32CD32}\n"+
"\\newcommand{\\Linen}{FAF0E6}\n"+
"\\newcommand{\\Magenta}{FF00FF}\n"+
"\\newcommand{\\Maroon}{B03060}\n"+
"\\newcommand{\\WebMaroon}{800000}\n"+
"\\newcommand{\\MediumAquamarine}{66CDAA}\n"+
"\\newcommand{\\MediumBlue}{0000CD}\n"+
"\\newcommand{\\MediumOrchid}{BA55D3}\n"+
"\\newcommand{\\MediumPurple}{9370DB}\n"+
"\\newcommand{\\MediumSeaGreen}{3CB371}\n"+
"\\newcommand{\\MediumSlateBlue}{7B68EE}\n"+
"\\newcommand{\\MediumSpringGreen}{00FA9A}\n"+
"\\newcommand{\\MediumTurquoise}{48D1CC}\n"+
"\\newcommand{\\MediumVioletRed}{C71585}\n"+
"\\newcommand{\\MidnightBlue}{191970}\n"+
"\\newcommand{\\MintCream}{F5FFFA}\n"+
"\\newcommand{\\MistyRose}{FFE4E1}\n"+
"\\newcommand{\\Moccasin}{FFE4B5}\n"+
"\\newcommand{\\NavajoWhite}{FFDEAD}\n"+
"\\newcommand{\\NavyBlue}{000080}\n"+
"\\newcommand{\\OldLace}{FDF5E6}\n"+
"\\newcommand{\\Olive}{808000}\n"+
"\\newcommand{\\OliveDrab}{6B8E23}\n"+
"\\newcommand{\\Orange}{FFA500}\n"+
"\\newcommand{\\OrangeRed}{FF4500}\n"+
"\\newcommand{\\Orchid}{DA70D6}\n"+
"\\newcommand{\\PaleGoldenrod}{EEE8AA}\n"+
"\\newcommand{\\PaleGreen}{98FB98}\n"+
"\\newcommand{\\PaleTurquoise}{AFEEEE}\n"+
"\\newcommand{\\PaleVioletRed}{DB7093}\n"+
"\\newcommand{\\PapayaWhip}{FFEFD5}\n"+
"\\newcommand{\\PeachPuff}{FFDAB9}\n"+
"\\newcommand{\\Peru}{CD853F}\n"+
"\\newcommand{\\Pink}{FFC0CB}\n"+
"\\newcommand{\\Plum}{DDA0DD}\n"+
"\\newcommand{\\PowderBlue}{B0E0E6}\n"+
"\\newcommand{\\Purple}{A020F0}\n"+
"\\newcommand{\\WebPurple}{800080}\n"+
"\\newcommand{\\RebeccaPurple}{663399}\n"+
"\\newcommand{\\Red}{FF0000}\n"+
"\\newcommand{\\RosyBrown}{BC8F8F}\n"+
"\\newcommand{\\RoyalBlue}{4169E1}\n"+
"\\newcommand{\\SaddleBrown}{8B4513}\n"+
"\\newcommand{\\Salmon}{FA8072}\n"+
"\\newcommand{\\SandyBrown}{F4A460}\n"+
"\\newcommand{\\SeaGreen}{2E8B57}\n"+
"\\newcommand{\\Seashell}{FFF5EE}\n"+
"\\newcommand{\\Sienna}{A0522D}\n"+
"\\newcommand{\\Silver}{C0C0C0}\n"+
"\\newcommand{\\SkyBlue}{87CEEB}\n"+
"\\newcommand{\\SlateBlue}{6A5ACD}\n"+
"\\newcommand{\\SlateGray}{708090}\n"+
"\\newcommand{\\Snow}{FFFAFA}\n"+
"\\newcommand{\\SpringGreen}{00FF7F}\n"+
"\\newcommand{\\SteelBlue}{4682B4}\n"+
"\\newcommand{\\Tan}{D2B48C}\n"+
"\\newcommand{\\Teal}{008080}\n"+
"\\newcommand{\\Thistle}{D8BFD8}\n"+
"\\newcommand{\\Tomato}{FF6347}\n"+
"\\newcommand{\\Turquoise}{40E0D0}\n"+
"\\newcommand{\\Violet}{EE82EE}\n"+
"\\newcommand{\\Wheat}{F5DEB3}\n"+
"\\newcommand{\\White}{FFFFFF}\n"+
"\\newcommand{\\WhiteSmoke}{F5F5F5}\n"+
"\\newcommand{\\Yellow}{FFFF00}\n"+
"\\newcommand{\\YellowGreen}{9ACD32}\n"+
"\n"+
"%To render this document in grayscale do:\n"+
"%xelatex \"\\def\\EvBUseGrayScale{1} \\input{thisfile.tex}\"\n"+
"%Or uncomment this line:\n"+
"%\\def\\EvBUseGrayScale{1}\n"+
"\n"+
"\\ifx\\EvBUseGrayScale\\undefined\n"+
"\\definecolor{ProvedColor}{HTML}{\\LimeGreen}%\n"+
"\\definecolor{NotProvedColor}{HTML}{\\Firebrick}%\n"+
"\\definecolor{ReviewedColor}{HTML}{\\CornflowerBlue}%\n"+
"\\definecolor{EvBId}{HTML}{880000}%\n"+
"\\definecolor{EvBKeyword}{HTML}{000000}%\n"+
"\\definecolor{EvBVariable}{HTML}{000000}%\n"+
"\\definecolor{EvBConstant}{HTML}{\\Indigo}%\n"+
"\\definecolor{EvBCarrierSet}{HTML}{\\LimeGreen}%\n"+
"\\definecolor{EvBPrimitiveSet}{HTML}{000000}%\n"+
"\\definecolor{EvBComment}{HTML}{000000}%\n"+
"\\definecolor{EvBLabel}{HTML}{0066cc}%\n"+
"\\definecolor{EvBNames}{HTML}{000000}%\n"+
"\\definecolor{EvBExpression}{HTML}{000000}%\n"+
"\\definecolor{EvBPredicate}{HTML}{000000}%\n"+
"\\definecolor{EvBNonFree}{HTML}{660000}%\n"+
"\\definecolor{EvBNumber}{HTML}{\\Orange}%\n"+
"\\definecolor{EvBAny}{HTML}{000000}%\n"+
"\\else\n"+
"\\definecolor{ProvedColor}{HTML}{888888}%\n"+
"\\definecolor{NotProvedColor}{HTML}{888888}%\n"+
"\\definecolor{ReviewedColor}{HTML}{888888}%\n"+
"\\definecolor{EvBId}{HTML}{000000}%\n"+
"\\definecolor{EvBKeyword}{HTML}{000000}%\n"+
"\\definecolor{EvBVariable}{HTML}{000000}%\n"+
"\\definecolor{EvBConstant}{HTML}{000000}%\n"+
"\\definecolor{EvBCarrierSet}{HTML}{000000}%\n"+
"\\definecolor{EvBPrimitiveSet}{HTML}{000000}%\n"+
"\\definecolor{EvBComment}{HTML}{000000}%\n"+
"\\definecolor{EvBLabel}{HTML}{000000}%\n"+
"\\definecolor{EvBNames}{HTML}{000000}%\n"+
"\\definecolor{EvBExpression}{HTML}{000000}%\n"+
"\\definecolor{EvBPredicate}{HTML}{000000}%\n"+
"\\definecolor{EvBNonFree}{HTML}{000000}%\n"+
"\\definecolor{EvBNumber}{HTML}{000000}%\n"+
"\\definecolor{EvBAny}{HTML}{000000}%\n"+
"\\fi\n"+
"\n"+
"\\newcommand{\\nl}[0]{ \\\\ }%\n"+
"\\newcommand{\\HRULE}[0]{\\rule{\\textwidth}{0.5pt}}%\n"+
"\\newcommand{\\LINE}[1]{#1 \\linebreak }%\n"+
"\\newcommand{\\LUFT}[0]{\\par \\vspace{-5mm}}%\n"+
"\n"+
"% Color/fontify keywords, identifiers, variables, constants, numbers, symbols etc.\n"+
"\\newcommand{\\ID}[1]{\\textrm{\\EVBTcolor{EvBId}#1\\index{#1}}}%\n"+
"\\newcommand{\\KEYW}[1]{\\noindent\\texttt{\\EVBTcolor{EvBKeyword}#1}}%\n"+
"\\newcommand{\\KEYWL}[1]{\\noindent\\texttt{\\EVBTcolor{EvBKeyword}#1}}%\n"+
"\\newcommand{\\VARDEF}[1]{\\textit{\\EVBTcolor{EvBVariable}#1}\\index{#1}}%\n"+
"\\newcommand{\\VAR}[1]{\\mathit{\\EVBTcolor{EvBVariable}#1}}%\n"+
"\\newcommand{\\CON}[1]{\\textrm{\\EVBTcolor{EvBConstant}#1}}%\n"+
"\\newcommand{\\CSET}[1]{\\textrm{\\EVBTcolor{EvBCarrierSet}#1}}%\n"+
"\\newcommand{\\PSET}[1]{\\textrm{\\EVBTcolor{EvBPrimitiveSet}#1}}%\n"+
"\\newcommand{\\EXPR}[1]{\\texttt{\\EVBTcolor{EvBExpression}#1}}%\n"+
"\\newcommand{\\PRED}[1]{\\texttt{\\EVBTcolor{EvBPredicate}#1}}%\n"+
"\\newcommand{\\NONF}[1]{\\mathit{\\EVBTcolor{EvBNonFree}#1}}%\n"+
"\\newcommand{\\NUM}[1]{{\\EVBTcolor{EvBNumber}#1}}%\n"+
"\\newcommand{\\ANY}[1]{\\textrm{\\EVBTcolor{EvBAny}#1}}%\n"+
"\\newcommand{\\INDENT}[0]{\\rule{5mm}{0mm}}%\n"+
"\n"+
"\\DeclareRobustCommand{\\ProvedAuto}[0]{%\n"+
" \\raisebox{-1mm}{%\n"+
" \\begin{tikzpicture}%\n"+
"    \\filldraw[color=ProvedColor, fill=ProvedColor, very thick](0,0) circle (1.5mm);%\n"+
"    \\node[color=white] {\\footnotesize a};%\n"+
"  \\end{tikzpicture}%\n"+
"  } %\n"+
"}\n"+
"\n"+
"\\DeclareRobustCommand{\\ProvedManual}[0]{%\n"+
" \\raisebox{-1mm}{%\n"+
" \\begin{tikzpicture}%\n"+
"    \\filldraw[color=ProvedColor, fill=ProvedColor, very thick](0,0) circle (1.5mm);%\n"+
"    \\node[color=black] {\\footnotesize \\raisebox{-0.2mm}[1ex][0ex]{i}};%\n"+
"  \\end{tikzpicture}%\n"+
"  } %\n"+
"}\n"+
"\n"+
"\\DeclareRobustCommand{\\Unproved}[0]{%\n"+
" \\lapbox[4mm]{-1mm}{%\n"+
" \\raisebox{-1.5mm}{%\n"+
" \\begin{tikzpicture}%\n"+
"   \\filldraw[color=NotProvedColor, fill=NotProvedColor, very thick] (-1.5mm,-1mm) node{} -- (0mm,1.8mm) node{} -- (1.5mm,-1mm) node{}  -- cycle;%\n"+
"  \\node[color=white] {\\tiny u};%\n"+
"  \\end{tikzpicture}%\n"+
" }%\n"+
" }%\n"+
"}\n"+
"\n"+
"\\DeclareRobustCommand{\\Reviewed}[0]{%\n"+
" \\raisebox{-1mm}{%\n"+
"  \\begin{tikzpicture}%\n"+
"    \\filldraw[color=ReviewedColor, fill=ReviewedColor, very thick](0,0) circle (1.5mm);%\n"+
"    \\node[color=white] {\\footnotesize r};%\n"+
"  \\end{tikzpicture}%\n"+
"  }%\n"+
"}\n"+
"\n"+
"% Adjust the underscore to be narrower.\n"+
"\\newcommand{\\UL}{\\kern-.1pt\\adjustbox{scale={0.5}{1},raise={0.1pt}{\\height}}{\\textunderscore}\\kern-.1pt}\n"+
"\n"+
"% Limit width of comments, labels, axioms, guards actions etc.\n"+
"\\newcommand{\\ACOM}[1]{\\textrm{\\EVBTcolor{EvBComment}#1} \\par\n"+
"}%\n"+
"\\newcommand{\\COM}[1]{\\textrm{\\EVBTcolor{EvBComment}#1}}\n"+
"\\newcommand{\\LAB}[1]{\\texttt{\\EVBTcolor{EvBLabel}#1}}%\n"+
"\\newcommand{\\INV}[1]{#1}%\n"+
"\\newcommand{\\AXM}[1]{#1}%\n"+
"\\newcommand{\\THM}[1]{#1}%\n"+
"\\newcommand{\\GRD}[1]{#1}%\n"+
"\\newcommand{\\WIT}[1]{#1}%\n"+
"\\newcommand{\\ACT}[1]{#1}%\n"+
"\n"+
"\\setlength\\LTpre{-2mm}\n"+
"\\setlength\\LTpost{-2mm}\n"+
"\\newcolumntype{R}{>{\\raggedright\\arraybackslash}X}\n"+
"\n"+
"\\newcommand{\\tlxmulticolumn}[3]\n"+
"    {\\multicolumn{#1}\n"+
"                 {>{\\hsize=\\dimexpr#1\\hsize+#1\\tabcolsep+\\arrayrulewidth\\relax}#2}\n"+
"                 {#3}}\n"+
"\n"+
"\\titleformat{\\section}[rightmargin]{}{\\fbox{\\thesection}}{0pt}{}[]\n"+
"\\titlespacing{\\section}{10mm}{*0.5}{*0.5}\n"+
"\\titleformat{\\subsection}[rightmargin]{}{\\fbox{\\thesubsection}}{0pt}{}[]\n"+
"\\titlespacing{\\subsection}{10mm}{*0.5}{*0.5}\n"+
"\\titleformat{\\subsubsection}[rightmargin]{}{\\fbox{\\thesubsubsection}}{0pt}{}[]\n"+
"\\titlespacing{\\subsubsection}{10mm}{*0.5}{*0.5}\n"+
"\n"+
"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n"+
"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n"+
"";
public static String HtmqFooter =
"    }\n"+
"}\n"+"";
public static String HtmlCss =
"body { }\n"+
".KEYW { display:block; color: red; margin-top:10pt; font-size:24px; }\n"+
".VARDEF { font-style: italic; }\n"+
".VAR { font-style: italic; }\n"+
".CON { color: blue; }\n"+
".LABEL { color: #0066cc };\n"+
"";
public static final String[] templates = {
"TeXHeader",TeXHeader,"TeXPackages",TeXPackages,"HtmqHeader",HtmqHeader,"TeXDefinitions",TeXDefinitions,"HtmqFooter",HtmqFooter,"HtmlCss",HtmlCss,"empty",empty};
}
