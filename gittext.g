grammar gittext;

//gernaral defentions
TEXT 		: ('A'..'Z' | 'a'..'z' | '0'..'9' | ',' | '"' | ':' | '?' | '.' | '/' | '_' | '\u000C')*;
WS  		: ( ' ' | '\t'  | '\r' | '\n' ) {$channel=HIDDEN;} ;
NL		    :'\\\\';
GENEND		:']';
GEN_SB		:'(';
GEN_SE		:')';

//start and end atributes
HEAD 		: '\\BEGIN';
END 		: '\\END';

//par atributes
PAR_B		:'\\PARB';
PAR_E		:'\\PARE';

//title atributes
TITLE_B		:'\\TITLE[' ;

//heading atribtes
HEADING_B	:'# ';

//bold atributes
BOLD_B		:'** ';
BOLD_E		:' **';

//ital atributes
ITAL_B		:'* ';
ITAL_E		:' *';

//unordered atribute
LIST_B		:'+ ';

//link atribute
LINK_FB		:'[';


//images atribute
IMAGE_FB	:'![';

//Var atributes
VAR_B 		:'\\DEF[';
VAR_M		:' = ';

//use var
VARISH_B	:'\\USE[';

//function defentions
var		    :VAR_B TEXT+ VAR_M TEXT+ GENEND;
varish		:VARISH_B TEXT+ GENEND;
heading		:HEADING_B (TEXT | varish)* NL;
title		:TITLE_B TEXT+ GENEND;
bold		:BOLD_B (TEXT | varish )* BOLD_E;
italics		:ITAL_B (TEXT|varish)* ITAL_E;
link		:LINK_FB TEXT+ GENEND GEN_SB TEXT+ GEN_SE;
image		:IMAGE_FB TEXT+ GENEND GEN_SB TEXT+ GEN_SE;
par		    :PAR_B var*(link | TEXT | bold | italics | WS | NL| varish)+ PAR_E;
list		:LIST_B (TEXT | bold | link | italics | varish)* NL*;

//actual parser
bod		    :(heading|bold|italics|link|image|par|list| varish|WS| TEXT | NL )+;
gittex		:HEAD var* title+ bod END;
