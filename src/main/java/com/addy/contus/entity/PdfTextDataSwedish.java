package com. addy. contus. entity;

public class PdfTextDataSwedish implements PdfTextData {
	
	public static String entryText="Denna rapport tillhör "; 
	
	public String result="Dina resultat";
	
	public String resultStatement1="Resultaten visar olika personlighetsdrag som social kompetens, förmåga att arbeta tillsammans, ordning, stress, " + 
			"motstånd, öppenhet för nya idéer och hur personen placerar sig på skalan. ";
	public String resultStatement2="Det finns inga rätta eller felaktiga svar. Testet visar snarare i vilken miljö personen känner och fungerar bäst. " + 
			"Den professionella rollen för hur personliga egenskaper kan stärka ett team bör avgöra vilka personlighetsdrag som är mest lämpliga. " + 
			"Alla poäng är på en skala från 1 till 100. ";
	
	public String score="Poäng : ";
	public String high="Hög";
	public String low ="Låg";
	public String neutral="Neutral";
	public String veryHigh="Hög";
	public String veryLow="Låg";
	
	public String facetN="Neuroticism";
	public String nDef="Neuroticism hänvisar till tendensen att uppleva negativa känslor. ";
	public String nHigh="Dina poäng ligger högt på Neuroticism, du kan beskylla dig själv när saker går fel. Du kan också lätt bli frustrerad över dig själv, speciellt om du gör ett misstag. "
					+ "Chansen är stor att du också är orolig. Men du är förmodligen också mer introvert än andra, vilket hjälper dig att undersöka och förstå dina känslor. ";
	public String nLow="Dina poäng ligger lågt på Neuroticism. Låga neurotiska poäng kan betyda att du är självsäker. Du kan ha mer motståndskraft och tycker att det är lätt att hålla sig lugn under stress. "
						+ "Avslappning kan också komma lättare för dig. Försök att komma ihåg att det här kanske inte är så lätt för dem omkring dig, så var tålmodig. ";
	public String nNeutral="Dina poäng är genomsnittliga för Neuroticism, vilket innebär att du inte alltid stressar för små saker. Du kan vara självsäker i vissa situationer. "
						+ "Du kanske inte blir lätt frustrerad men är inte heller immun mot den. ";
	public String nInfo="Freud använde ursprungligen termen neuros för att beskriva ett tillstånd som kännetecknas av psykisk nöd, emotionellt lidande och en" + 
			"oförmåga att hantera de normala livskraven effektivt. Han antydde att alla skulle visa några tecken på neuros, " + 
			"men att vi skiljer oss åt i vår grad av lidande och våra specifika symtom på nöd. Idag hänvisar neuroticism till" + 
			"tendensen att uppleva negativa känslor. De som har höga poäng på Neuroticism kan uppleva främst en" + 
			"specifik negativ känsla som ångest, ilska eller depression, men kommer sannolikt att uppleva flera av dessa känslor. " + 
			"Människor som ligger högt på skalan i Neuroticism är känslomässnigt reaktiva. De svarar känslomässigt på händelser som inte påverkar mest" + 
			"människor, och deras reaktioner tenderar att vara mer intensiva än normalt. De är mer benägna att tolka vanliga situationer som" + 
			"hotande och mindre frustrationer som hopplöst svåra. Deras negativa känslomässiga reaktioner tenderar att kvarstå " + 
			"ovanligt långa tidsperioder, vilket innebär att de ofta har dåligt humör. Dessa problem i emotionell reglering" + 
			"kan minska en neurotisk förmåga att tänka klart, fatta beslut och hantera stress effektivt. ";
	
	public String facetN1="Ångest";
	public String n1Info="Hjärnans flykt- och kamprespons av oroliga människor aktiveras alldeles för ofta och för lätt. Av den anledningen " + 
			"har personer som ligger högt på ångestskalan känner ofta som något farligt är på väg att hända. De kan vara rädda för specifika " + 
			"situationer eller vara i allmänhet rädda. De känner sig spända, skakiga och nervösa. Personer med låg ångest är i allmänhet lugna " + 
			"och orädda. ";
	public String facetN2="Ilska";
	public String n2Info="Personer som får höga poäng i Ilska känner sig upprörda när saker inte går som de ska. De är känsliga för att bli behandlade " + 
			"rättvist och känner sig stötta och bittra när de känner att de luras. Denna skala mäter tendensen att känna " + 
			"ilska; huruvida personen uttrycker irritation och fientlighet beror på individens nivå på " + 
			"Behaglighet/Vänlighet. Personer som ligger lågt på skalan blir inte lätt eller ofta arga. ";
	public String facetN3="Depression";
	public String n3Info="Denna skala mäter tendensen att känna sig ledsen, nedslagen och avskräckt. Personer som ligger högt på skalan " + 
			"saknar energi och har svårt att initiera aktiviteter. Personer med låga poäng tenderar att vara fria från dessa depressiva känslor. ";
	public String facetN4="Självmedvetenhet";
	public String n4Info="Självmedvetna individer är känsliga för vad andra tycker om dem. Deras oro över avslag och förlöjligande " + 
			"får dem att känna sig blyga och obekväma i överflöd. De är lätt generade och skäms ofta. Deras " + 
			"rädsla för att andra kommer att kritisera eller göra narr av dem är överdrivna och orealistiska, men deras besvärlighet och " + 
			"obehag kan göra dessa farhågor till en självuppfyllande profetia. Däremot lider lågpoängare inte av det felaktiga " + 
			"intrycket av att alla tittar och bedömer dem. De känner sig inte nervösa i sociala situationer. ";
	public String facetN5="Immoderation";
	public String n5Info="Dessa personer känner starka begär och hävdar att de har svårt att kontrollera sig. De tenderar att vara orienterade " + 
			"mot kortsiktiga nöjen och belöningar snarare än långsiktiga konsekvenser. Lågpoängare upplever inte starka, " + 
			"oemotståndliga begär och frestas följaktligen inte att överdriva. ";
	public String facetN6="Sårbarhet";
	public String n6Info="Folk med höga poäng på Sårbarhet upplever panik, förvirring och hjälplöshet under press eller stress. Lågpoängare " + 
			"känner sig mer redo, självsäkra och klartänkande när de är stressade. ";
	
	
//***************************************************************************************************************
	
	public String facetE="Extraversion";
	public String eDef="Extraversion präglas av ett uttalat engagemang med den yttre världen. ";
	public String eHigh="Dina poäng är höga på Extraversion, du kan betrakta dig själv som en extrovert. Du kan njuta av uppmärksamhet och känna dig laddad efter att ha spenderat tid med vänner. "
				+ "Du känner dig troligen bäst när du är i en stor grupp människor. Å andra sidan kan du ha problem med att spendera långa perioder ensam. ";
	public String eLow="Dina poäng är låga på Extraversion. Låga poäng på extraversionskalan kan innebära att du föredrar att spendera tid ensam eller med en liten grupp nära vänner. Du kan också vara en mer privat person när det"
					+ "kommer att dela detaljer om ditt liv. Detta kan uppfattas som reserverat för andra. ";
	public String eNeutral="Dina poäng är genomsnittliga för Extraversion. Du kanske gillar att gå ut och festa en eller två gånger, men du är inte en vanlig partymänniska. Du behöver inte alltid uppmärksammas av människor "
				+ "men du gillar att bli uppskattad för ditt arbete. ";
	public String eInfo="Extroverta personer tycker om att vara med människor, är fulla av energi och upplever ofta positiva känslor. De brukar vara " + 
			"entusiastiska, handlingsorienterade, individer som sannolikt kommer att säga \" Ja! \" eller \"Kom igen! \" till möjligheter till spänning. I " + 
			"grupper gillar de att prata, hävda sig och uppmärksamma sig själva. Introverter saknar överflödet av energi, " + 
			"och aktivitetsnivåer som extroverter har. De tenderar att vara tysta, lågmälda, medvetna och urkopplade från den sociala världen. Deras " + 
			"brist på socialt engagemang bör inte tolkas som blyghet eller depression; den introverta behöver helt enkelt mindre " + 
			"stimulering än en extrovert och föredrar att vara ensam. Introvertens självständighet och reservation misstas ibland " + 
			"som ovänlighet eller arrogans. I verkligheten kommer en introvert som har höga poäng på Vänlighet (Agreeableness) " + 
			"inte söka efter andra men kommer att vara ganska trevlig när han/hon kontaktas. ";
	
	public String facetE1="Vänlighet";
	public String e1Info="Vänliga människor tycker genuint om andra människor och visar öppet positiva känslor mot andra. De skapar " + 
			"vänner snabbt och det är lätt för dem att skapa nära, intima relationer. Lågpoängare är inte " + 
			"nödvändigtvis kalla och fientliga, men de når inte ut till andra uppfattas dem som avlägsna och reserverade. ";
	public String facetE2="Sällskaplighet";
	public String e2Info="Sällskapliga människor tycker att andras sällskap är trevligt stimulerande och givande. De njuter av spänningen i " + 
			"folkmassor. Lågpoängare tenderar att känna sig överväldigade av folkmassor och undviker därför aktivt stora folksamlingar. De behöver inte nödvändigtvis " + 
			"ogilla att vara med människor ibland, men deras behov av integritet och tid för sig själva är mycket större än för " + 
			"individer som gör höga poäng på denna skala. ";
	public String facetE3="Självsäkerhet";
	public String e3Info="Högpoängare i Självsäkerhet tycker om att tala ut, ta ansvar och styra andras aktiviteter. De brukar vara ledare i " + 
			"grupper. Personer med låga poäng tenderar att inte prata mycket och låta andra kontrollera gruppens aktiviterer. ";
	public String facetE4="Aktivitetsnivå";
	public String e4Info="Aktiva individer lever snabba, livliga liv. De rör sig snabbt, energiskt och kraftfullt, och de är " + 
			"involverade i många aktiviteter. Människor som får låga poäng på denna skala anammar en långsammare och lugnare, avslappnad takt. ";
	public String facetE5="Spänningssökande";
	public String e5Info="Höga poängare på denna skala blir lätt uttråkade utan höga nivåer av stimulering. De älskar liv och rörelse. " + 
			"De kommer sannolikt att ta risker och söka spänning. Lågpoängare är överväldigade av buller och uppståndelse och är " + 
			"negativa mot spänningssökning. ";
	public String facetE6="Gladlynthet";
	public String e6Info="Denna skala mäter positivt humör och känslor, inte negativa känslor (som är en del av Neuroticism-området). " + 
			"Personer som får höga poäng på denna skala upplever vanligtvis en rad positiva känslor, inklusive lycka, entusiasm, " + 
			"optimism och glädje. Personer med låga poäng är inte lika öppna för sådant energiskt, högt humör. ";
	
//***************************************************************************************************************
	public String facetO="Öppenhet";
	public String oDef="Öppenhet att Uppleva handlar om människors vilja att försöka nya saker, deras förmåga att vara sårbara och deras förmåga att tänka outside the box. ";
	public String oHigh="Dina poäng på Öppenhet att Uppleva är höga. Höga poäng här kan betyda att du har breda intressen. Du kanske tycker om att lösa problem med nya metoder och tycker att det är lätt att "
			+ "tänka på saker på olika sätt. Att vara öppen för nya idéer kan hjälpa dig att enkelt anpassa dig till förändringar. Se bara till att hålla ett öga på eventuella situationer där du eventuellt "
			+ "behöver skapa gränser, oavsett om det är med familjemedlemmar eller din balans mellan arbete och privatliv. ";
	public String oLow="Dina poäng på Öppenhet att Uppleva är låga. Låga poäng här kan innebära att du ser begrepp på ett enkelt/direkt sätt. "
			+ "Andra ser dig troligen som jordnära. Du föredrar rutin framför variation, håller fast vid det du vet och föredrar mindre abstrakt konst och underhållning. ";
	public String oNeutral="Dina poäng på Öppenhet att Upplevela är genomsnittliga, vilket indikerar att du gillar tradition men är villig att prova nya saker. " + 
			"Ditt tänkande är varken enkelt eller komplext. För andra verkar du vara en välutbildad person men inte " + 
			"intellektuell. ";
	public String oInfo="Öppna människor är intellektuellt nyfikna, uppskattar konst och är känsliga för skönhet. De brukar vara, jämfört med " + 
			"stängda människor, mer medvetna om sina känslor. De tenderar att tänka och agera på individualistiska och avvikande sätt. " + 
			"Intellektuella får vanligtvis höga poäng för Öppenhet att Uppleva; följaktligen har denna faktor också kallats kultur eller " + 
			"intellekt. Ändå betraktas intellekt troligen bäst som en aspekt av Öppenhet att Uppleva. Poäng på " + 
			"Öppenhet att Uppleva är bara blygsamt relaterade till år av utbildning och får poäng på vanliga intelligentstester. " + 
			"En annan egenskap hos den öppna kognitiva stilen är en förmåga att tänka i symboler och abstraktioner långt ifrån " + 
			"konkret erfarenhet. Beroende på individens specifika intellektuella förmågor kan denna symboliska kognition kan " + 
			"kännetecknas i form av matematiskt, logiskt eller geometriskt tänkande, konstnärlig och metaforisk användning av språk, musikkomposition eller " + 
			"prestanda, eller en av de många visuella eller scenkonsterna. Människor med låga poäng på Öppenhet att Uppleva tenderar att " + 
			"ha smala, gemensamma intressen. De föredrar det vanliga, raka och uppenbara framför det komplexa, tvetydiga och " + 
			"subtila. De kan betrakta konst och vetenskap med misstänksamhet och betraktar dessa ansträngningar som svårfattliga eller utan praktisk " + 
			"användning. Stängda människor föredrar förtrogenhet framför okonventionalitet; de är konservativa och motsätter sig mot förändringar. Öppenhet är ofta " + 
			"framställt som friskare eller mognare av psykologer, som ofta själva är öppna för upplevelse. Öppna " + 
			"och slutna tankesätt är dock användbara i olika miljöer. Den öppna personens intellektuella stil kan tjäna en " + 
			"professor väl, men forskning har visat att slutet tänkande är relaterat till överlägsen arbetsprestanda i polisarbete, försäljning, " + 
			"och ett antal tjänstearbeten. ";
	
	public String facetO1="Fantasi";
	public String o1Info="För fantasifulla individer är den verkliga världen ofta för enkel och vanlig. Personer med höga poäng på denna skala använder fantasi som ett sätt " + 
			"att skapa en rikare, mer intressant värld. Lågpoängare på denna skala är mer orienterade mot fakta än fantasi. ";
	public String facetO2="Konstnärliga intressen";
	public String o2Info="Personer med höga poäng på denna skala älskar skönhet, både i konst och i naturen. De blir lätt involverade och absorberade av konstnärliga " + 
			"och naturliga händelser. De är inte nödvändigtvis konstnärligt utbildade eller begåvade, även om många kommer att bli. De definierande " + 
			"dragen på denna skala är intresse för och uppskattning av naturlig och artificiell skönhet. Personer med låga poäng saknar estetisk " + 
			"känslighet och intresse för konst. ";
	public String facetO3="Emotionalitet";
	public String o3Info="Personer med hög känslomässighet har god tillgång till och medvetenhet om sina egna känslor. Personer med låga poäng är mindre medvetna om " + 
			"deras känslor och tenderar att inte uttrycka sina känslor öppet. ";
	public String facetO4="Äventyrlighet";
	public String o4Info="Personer med höga poäng på Äventyrlighet är ivriga att prova nya aktiviteter, resa till främmande länder och uppleva olika saker. " + 
			"De tycker att bekantskap och rutin är tråkigt och tar en ny väg hem bara för att den är annorlunda. Personer med låga poäng tenderar att " + 
			"känna sig obekväma med förändringar och föredrar välbekanta rutiner. ";
	public String facetO5="Intellekt";
	public String o5Info="Intellekt och konstnärliga intressen är de två viktigaste, centrala aspekterna av Öppenhet att Uppleva. Personer med höga poäng på " + 
			"Intellekt älskar att leka med idéer. De är öppensinnade för nya och ovanliga idéer och gillar att diskutera intellektuella frågor. " + 
			"De njuter av gåtor, pussel och hjärnproblem. Lågpoängare på Intellekt föredrar att hantera antingen människor eller saker " + 
			"snarare än idéer. De ser intellektuella övningar som slöseri med tid. Intellekt bör inte likställas med " + 
			"intelligens. Intellekt är en intellektuell stil, inte en intellektuell förmåga, även om personer med höga poäng på Intellekt får lite " + 
			"högre än personer med låga poäng, på standardiserade intelligenstester. ";
	public String facetO6="Liberalism";
	public String o6Info="Psykologisk liberalism avser en förmåga att utmana auktoritet, konvention och traditionella värderingar. I mest " + 
			"extrema formen kan psykologisk liberalism till och med representera fullständig fientlighet mot regler, sympati för lagbrytare, " + 
			"och kärlek till tvetydighet, kaos och oordning. Psykologiska konservativa föredrar säkerhet och stabilitet skapat av " + 
			"anpassningen till tradition. Psykologisk liberalism och konservatism är inte identisk med politisk tillhörighet, men " + 
			"pushar säkert personer mot vissa politiska partier. ";
	
//***************************************************************************************************************
	
	public String facetA="Vänlighet/Behaglighet";
	public String aDef="Vänlighet återspeglar individuella skillnader i fråga om samarbete och social harmoni. ";
	public String aHigh="Dina poäng är höga i behaglighet. Du är hjälpsam och samarbetsvillig. Dina nära och kära kan ofta vända sig till dig för att få hjälp. "
			+ "Folk kan se dig som pålitlig. Du kan vara den person som andra söker sig till när de försöker lösa en oenighet. "
			+ "I vissa situationer kanske du är lite för förtroende eller villig att kompromissa. Försök att balansera din förmåga att glädja andra med egna självintressen. ";
	public String aLow="Dina poäng är låga i behaglighet. Låga poäng kan innebära att du tenderar att vara långsint. Du kan också vara mindre sympatisk med andra. Men du undviker troligtvis också "
						+ "fallgroparna att jämföra dig med andra eller bry dig om vad andra tycker om dig. ";
	public String aNeutral="Dina poäng är genomsnittlig i behaglighet. Du är alltid redo att hjälpa andra men kan vara envis med dina idéer. Du bryr dig men kan vara självcentrerad samtidigt. "
						+ "Du kanske håller med om andras idéer men vill alltid ha något att säga till om. ";
	public String aInfo="Behagliga människor är omtänksamma, vänliga, generösa, hjälpsamma och villiga att kompromissa sina intressen med andras. " + 
			"Behagliga människor har också en optimistisk syn på människans natur. De tror att människor i grunden är ärliga, anständiga och " + 
			"pålitliga. Mindre omtyckta personer lägger egna intressen framför att komma överens med andra. I allmänhet " + 
			"bryr de sig inte om andras välbefinnande och anstränger sig sannolikt inte för andra människor. Ibland " + 
			"deras skeptisism mot andras planer får dem att vara misstänksamma, ovänliga och osamarbetsvilliga. Behaglighet är " + 
			"uppenbarligen fördelaktigt för att uppnå och bibehålla popularitet. Behagliga människor är bättre gillade än obehagliga " + 
			"människor. Å andra sidan är Vänlighet inte användbart i situationer som kräver hårda eller definitiva, objektiva beslut. " + 
			"Mindre trevliga människor kan göra utmärkta forskare, kritiker eller soldater. ";
	
	public String facetA1="Tillit";
	public String a1Info="En person med stor tillit antar att de flesta är rättvisa, ärliga och har goda avsikter. Personer med låg tillit ser " + 
			"andra som själviska, slarviga och potentiellt farliga. ";
	public String facetA2="Moral";
	public String a2Info="Personer med höga poäng på denna skala ser inget behov av simulera eller manipulera när de har att göra med andra och är därför " + 
			"uppriktiga, direkta och ärliga. Personer med låga poäng tror att en viss villfarelse i sociala relationer är nödvändig. " + 
			"Människor tycker att det är relativt lätt att relatera till de direkta högpoängare på denna skala. De hittar i allmänhet " + 
			"svårare att relatera till de okomplicerade lågpoängare på denna skala. Det bör göras tydligt att personer med låga poäng inte är " + 
			"principlösa eller omoraliska; de är helt enkelt mer reserverade och mindre villiga att öppet avslöja hela sanningen. ";
	public String facetA3="Altruism";
	public String a3Info="Altruistiska människor tycker att det är mycket givande att hjälpa andra människor. Följaktligen är de i allmänhet villiga att hjälpa " + 
			"de som är i nöd. Altruistiska människor tycker att göra saker för andra är en form av självuppfyllelse snarare än självuppoffring. Personer med låga poäng på denna skala gillar inte särskilt mycket att hjälpa dem i nöd. Begäran om hjälp känns som en " + 
			"börda snarare än en möjlighet till självuppfyllelse. ";
	public String facetA4="Sammarbete";
	public String a4Info="Individer som gör höga poäng på denna skala ogillar konfrontationer. De är mycket villiga att kompromissa eller förneka " + 
			"sina egna behov för att komma överens med andra. De som gör låg poäng på denna skala är mer benägna att skrämma " + 
			"andra för att få sin vilja genom. ";
	public String facetA5="Anspråkslöshet";
	public String a5Info="Personer med höga poäng på denna skala gillar inte att hävda att de är bättre än andra människor. I vissa fall kan denna attityd " + 
			"härröra från lågt självförtroende eller självkänsla. Ändå finner vissa människor med hög självkänsla oblyghet " + 
			"opassande. De som är villiga att beskriva sig själva som överlägsna tenderar att betraktas som obehagligt arroganta av andra " + 
			"människor ";
	public String facetA6="Sympati";
	public String a6Info="Människor som får höga poäng på denna skala är ömsinta och medkännande. De känner andras smärta istället och " + 
			"har lätt att tycka synd om dem. Personer med låga poäng påverkas inte starkt av mänskligt lidande. De är stolta över att göra " + 
			"objektiva bedömningar baserade på förnuft. De är mer intresserade av sanning och opartisk rättvisa än av barmhärtighet. ";

//***************************************************************************************************************
	
	public String facetC="Samvetsgrannhet";
	public String cDef="Samvetsgrannhet handlar om hur vi kontrollerar, reglerar och riktar våra impulser. ";
	public String cHigh="Du är en samvetsgrann person, du kan följa ett vanligt schema och har förmåga att hålla reda på detaljer. "
			+ "Du överväger troligen alternativ och jobbar hårt för att uppnå dina mål. Medarbetare och vänner kan se dig som en pålitlig, rättvis person. "
			+ "Du kan tendera att hantera situationer eller uppgifter. Du kan också vara försiktig eller svår att behaga. ";
	public String cLow="Människor med låg samvetsgrannhet är mindre intresserade av långsiktiga mål och mer intresserade av att besvara ögonblickligen. De gillar mer att ha roligt än hårtarbetande personer och distraheras lätt. "
			+ "Människor med låg samvetsgrannhet tenderar att överge planerna lätt när något mer attraktivt uppstår. De är ofta oorganiserade och utför uppgifter på ett slumpmässigt sätt. ";
	public String cNeutral="Dina poäng för Conscientiousness är genomsnittliga. Du är organiserad person för saker med prioritet men kanske inte är så mycket organiserad för vanliga dagliga aktiviteter. "
						+ "Du kan vara svårt att behaga i början, men när folk lär känna dig kan dem finna dig pålitlig. ";
	public String cInfo="Samvetsgrannhet verkar vara fundamentalt relaterad till impulskontroll eller vår förmåga att hindra oss från att göra det som kan vara roligt eller tilltalande just nu för att sträva efter ett viktigare långsiktigt mål. Vi hänvisar oftare till detta som viljestyrka i vardagsspråket. " + 
			"Impulskontroll är associerad med hjärnans främre lob, där våra högsta tankegångar äger rum. Frontloberna styr högplanering, problemlösning, bedömning och andra viktiga kognitiva förmågor. Frontloberna är den del av hjärnan som gör oss unikt mänskliga; de tar de mer djuriska impulserna som skickas av resten av hjärnan (\"Det finns mat där! Ät!\") och modulerar dem så att de passar våra mer komplexa planer och mål (\"Jag ska inte äta den här munken för jag försöker att gå ner i vikt. \"). " + 
			"I en hjärnbildningsstudie visade personer med hög samvetsgrannhet en högre aktivitetsnivå i frontlobberna när de utförde en uppgift som krävde att de kontrollerade sitt beteende. Detta antyder att hjärnområden som är ansvariga för att kontrollera impulser är mer aktiva hos människor med hög samvetsgrannhet. ";
	
	public String facetC1="Själveffektivitet";
	public String c1Info="Själveffektivitet beskriver förtroendet för ens förmåga att åstadkomma saker. Personer med höga poäng tror att de har intelligensen " + 
			"(sunt förnuftet), drivkraften och självkontrollen som krävs för att uppnå framgång. Personer med låga poäng känner sig inte effektiva och kan " + 
			"ha en känsla av att de inte har kontroll över sina liv. ";
	public String facetC2="Ordning";
	public String c2Info="Personer med höga poäng på Ordning är välorganiserade. De gillar att leva enligt rutiner och scheman. De " + 
			"skapar listor och gör planer. Personer med låga poäng tenderar att vara oorganiserade och slarviga. ";
	public String facetC3="Plikttrogenhet";
	public String c3Info="Denna skala återspeglar styrkan i en persons känsla av plikt och skyldighet. De som får höga poäng på denna skala har en " + 
			"stark känsla av moralisk skyldighet. Personer med låga poäng tycker att kontrakt, regler och regler är alltför begränsande. De kommer sannolikt att " + 
			"ses som opålitliga eller till och med oansvariga. ";
	public String facetC4="Prestationssträvan";
	public String c4Info="Individer som får höga poäng på denna skala strävar hårt för att uppnå excellens. Deras strävan att bli erkända som framgångsrika " + 
			"håller dem på rätt väg mot sina höga mål. De har ofta en stark riktning i livet, men personer med extremt höga " + 
			"poäng kan vara för ensamma och besatta av sitt arbete. Personer med låga poäng är nöjda med att klara sig med minimalt " + 
			"arbete och kan uppfattas som lata. ";
	public String facetC5="Självdisciplin";
	public String c5Info="Självdisciplin - vad många kallar viljestyrka - hänvisar till förmågan att fortsätta vid svåra eller obehagliga uppgifter tills de " + 
			"är genomförda. Människor som har hög självdisciplin kan övervinna motvilligheten att påbörja uppgifter och fortsätta " + 
			"trots distraktioner. Personer med låg självdisciplin fördröjer och visar dålig uppföljning och ofta misslyckas med att " + 
			"slutföra uppgifterna - även dem som de gärna vill slutföra ";
	public String facetC6="Försiktighet";
	public String c6Info="Försiktighet beskriver dispositionen att tänka igenom möjligheter innan man agerar. Personer med höga poäng på Försiktighet " + 
			"tar sin tid när de fattar beslut. De med låga poäng säger eller gör ofta det första de tänker på utan att " + 
			"överväga alternativ och de potentiella konsekvenserna av dessa alternativ. ";
	
	public String getResult() {
		return result;
	}
	public String getResultStatement1() {
		return resultStatement1;
	}
	public String getResultStatement2() {
		return resultStatement2;
	}
	public String getScore() {
		return score;
	}
	public String getHigh() {
		return high;
	}
	public String getLow() {
		return low;
	}
	public String getNeutral() {
		return neutral;
	}
	public String getVeryHigh() {
		return veryHigh;
	}
	public String getVeryLow() {
		return veryLow;
	}
	public String getFacetN() {
		return facetN;
	}
	public String getnDef() {
		return nDef;
	}
	public String getnHigh() {
		return nHigh;
	}
	public String getnLow() {
		return nLow;
	}
	public String getnNeutral() {
		return nNeutral;
	}
	public String getnInfo() {
		return nInfo;
	}
	public String getFacetN1() {
		return facetN1;
	}
	public String getN1Info() {
		return n1Info;
	}
	public String getFacetN2() {
		return facetN2;
	}
	public String getN2Info() {
		return n2Info;
	}
	public String getFacetN3() {
		return facetN3;
	}
	public String getN3Info() {
		return n3Info;
	}
	public String getFacetN4() {
		return facetN4;
	}
	public String getN4Info() {
		return n4Info;
	}
	public String getFacetN5() {
		return facetN5;
	}
	public String getN5Info() {
		return n5Info;
	}
	public String getFacetN6() {
		return facetN6;
	}
	public String getN6Info() {
		return n6Info;
	}
	public String getFacetE() {
		return facetE;
	}
	public String geteDef() {
		return eDef;
	}
	public String geteHigh() {
		return eHigh;
	}
	public String geteLow() {
		return eLow;
	}
	public String geteNeutral() {
		return eNeutral;
	}
	public String geteInfo() {
		return eInfo;
	}
	public String getFacetE1() {
		return facetE1;
	}
	public String getE1Info() {
		return e1Info;
	}
	public String getFacetE2() {
		return facetE2;
	}
	public String getE2Info() {
		return e2Info;
	}
	public String getFacetE3() {
		return facetE3;
	}
	public String getE3Info() {
		return e3Info;
	}
	public String getFacetE4() {
		return facetE4;
	}
	public String getE4Info() {
		return e4Info;
	}
	public String getFacetE5() {
		return facetE5;
	}
	public String getE5Info() {
		return e5Info;
	}
	public String getFacetE6() {
		return facetE6;
	}
	public String getE6Info() {
		return e6Info;
	}
	public String getFacetO() {
		return facetO;
	}
	public String getoDef() {
		return oDef;
	}
	public String getoHigh() {
		return oHigh;
	}
	public String getoLow() {
		return oLow;
	}
	public String getoNeutral() {
		return oNeutral;
	}
	public String getoInfo() {
		return oInfo;
	}
	public String getFacetO1() {
		return facetO1;
	}
	public String getO1Info() {
		return o1Info;
	}
	public String getFacetO2() {
		return facetO2;
	}
	public String getO2Info() {
		return o2Info;
	}
	public String getFacetO3() {
		return facetO3;
	}
	public String getO3Info() {
		return o3Info;
	}
	public String getFacetO4() {
		return facetO4;
	}
	public String getO4Info() {
		return o4Info;
	}
	public String getFacetO5() {
		return facetO5;
	}
	public String getO5Info() {
		return o5Info;
	}
	public String getFacetO6() {
		return facetO6;
	}
	public String getO6Info() {
		return o6Info;
	}
	public String getFacetA() {
		return facetA;
	}
	public String getaDef() {
		return aDef;
	}
	public String getaHigh() {
		return aHigh;
	}
	public String getaLow() {
		return aLow;
	}
	public String getaNeutral() {
		return aNeutral;
	}
	public String getaInfo() {
		return aInfo;
	}
	public String getFacetA1() {
		return facetA1;
	}
	public String getA1Info() {
		return a1Info;
	}
	public String getFacetA2() {
		return facetA2;
	}
	public String getA2Info() {
		return a2Info;
	}
	public String getFacetA3() {
		return facetA3;
	}
	public String getA3Info() {
		return a3Info;
	}
	public String getFacetA4() {
		return facetA4;
	}
	public String getA4Info() {
		return a4Info;
	}
	public String getFacetA5() {
		return facetA5;
	}
	public String getA5Info() {
		return a5Info;
	}
	public String getFacetA6() {
		return facetA6;
	}
	public String getA6Info() {
		return a6Info;
	}
	public String getFacetC() {
		return facetC;
	}
	public String getcDef() {
		return cDef;
	}
	public String getcHigh() {
		return cHigh;
	}
	public String getcLow() {
		return cLow;
	}
	public String getcNeutral() {
		return cNeutral;
	}
	public String getcInfo() {
		return cInfo;
	}
	public String getFacetC1() {
		return facetC1;
	}
	public String getC1Info() {
		return c1Info;
	}
	public String getFacetC2() {
		return facetC2;
	}
	public String getC2Info() {
		return c2Info;
	}
	public String getFacetC3() {
		return facetC3;
	}
	public String getC3Info() {
		return c3Info;
	}
	public String getFacetC4() {
		return facetC4;
	}
	public String getC4Info() {
		return c4Info;
	}
	public String getFacetC5() {
		return facetC5;
	}
	public String getC5Info() {
		return c5Info;
	}
	public String getFacetC6() {
		return facetC6;
	}
	public String getC6Info() {
		return c6Info;
	}
	
	
	
	
	
	
	
	
	
	
	
}
