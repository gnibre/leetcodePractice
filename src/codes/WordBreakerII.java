package codes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;



// find all of them this time.

public class WordBreakerII {
    public void go(){
    	
//    	String[] ar = {"cbc","bcda","adb","ddca","bad","bbb","dad","dac","ba","aa","bd","abab","bb","dbda","cb","caccc","d","dd","aadb","cc","b","bcc","bcd","cd","cbca","bbd","ddd","dabb","ab","acd","a","bbcc","cdcbd","cada","dbca","ac","abacd","cba","cdb","dbac","aada","cdcda","cdc","dbc","dbcb","bdb","ddbdd","cadaa","ddbc","babb"};
//    	String[] ar = {"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"};
//    	String[] ar ={"be","ellaekgjhibcomc","ahaklkan","jcm","lchidklmcone","ljmdgagaen","giioojldjkfnno","el","eibjaffacjll","hn","hbjakhjneml","foi","nbhf","aigf","cfdlnc","fa","amakgofedhkghgl","ddhmhdhioh","ijoddeabbiei","giamcgco","nholghlfbbendi","emlc","m","elgibme","behkignjenmh","lodkkjgioe","doe","hgflgakna","macghogdidmdm","ec","kncigolkog","ljooio","lch","gkaclkbjn","ofiaglfoffl","alhfbb","cfmdbgo","cfcnajknk","jfh","almbgdjnbbbgmhb","dmlnnohf","olojeejfafc","ndgcmgoe","cmkdjilfeo","bengdd","enfg","dbngiggni","anmkljn","njdnjdmmfha","ndimmddfmhe","hmkdjkhhiilnf","ehd","jfdl","dlgki","bhoflnomibkki","lg","fjojjnnkdfenhol","lefhhl","nimdl","gejgomblmim","ahbnlmlfmejjj","glhacaojnf","mfjdhnhdkm","do","fnh","mnmjdk","hfjgdlnnb","hniolfhkhbie","ldgodonogcab","mabjnnohnijhn","aceojlkmdg","aedfljg","cehk","jag","oniegflnhje","jo","maokc","jkbndc","djbn","dajkdblojkf","dmen","kcdjdocinenc","cgindbm","h","odaof","cmogcbgj","anjahlgbbmba","haoe","ggacnminj","ilcfjoedhe","klookammgofl","onnmenn","mbdneaioo","jc","dekgil","bjdfibfd","hfbnlgmlllcb","afebehf","obekljbnh","eoaedhjk","nobamccd","mdieojoknf","komcglmakkaa","jcliimcc","jmmgbmha","gdogjnn","ednembco","dgno","jiaheeabifahfmo","djkcgnakkh","kdkiglgf","eb","fmdnlhj","eicohdciejc","jgofmankkf","o","nnmomkmkmiaoga","njchkccln","ndamha","eleanmojdi","ebkl","jcageehlelcg","acfddofjihgmn","iklaomfhjm","io","igmob","lfnhnlnigbbignk","anihfojmedf","nj","oilcabalhb","adjfbkfjch","lbfb","mgfnngfccb","jhmhggm","dnllc","c","ljim","jmikd","mdfimdgac","fhbclgo","edclcdia","nelmfjejff","i","cmcbbckdnjcoo","cddocce","hc","keh","keofhnhemd","biln","mjcnbjmkikon","fekbdnkolahh","hkfmjbj","mjoj","jn","ilof","ifhfk","aofmg","nofljgmmmf","hcdifeiclbchlf","imlijgdg","ocdiiemcmbkglm","nhoekmlkjfoa","kibffkbleedda","kdhdjekccbkc","bcbflcag","jekmkdimnnjjoo","mmgfljchalbem","kchk","oi","ncf","jembgfa","l","kfkeianmmmdacl","ecjkkfggj","jdgcfnhfjonkhig","jhagiokii","nifm","bbjjlj","adajlokomibfg","ojk","lockdel","bh","hoojolglchck","conko","eadi","kfigoijnfimolen","g","dbnj","cojkbmo","hh","mcdbh","ngdmgioen","ehjagfohnolkho","dgfgdlc","aoglneoh","gbc","ijjckddeicld","imekih","liiaecniil","hahejbhgiclb","fnmojm","ablihjhggiahhno","colloaakco","jhobddaanbhmlg","cbfajfhkoh","cim","laghknigabn","dcbnbkegkjam","gem","ljjim","icclogji","omidhe","f","giiaclfcjkagl","ndcjldekjnkekm","aiikdccohcj","mkbmb","oomhhafobic","bkacdjfgbggn","ahghdoahbi","hedm","eeoj","bdgdlfollegej","eg","dfeb","dkffkid","hcne","gjkohnaaabn","jfeododjgdhlfbf","clfkmconnkfb","abnbkcni","hk","ghnmhjm","oibjibmkaibdefa","hjambim","oe","aao","jil","fmhomflfen","hlidcklnmb","hiaonkhd","bibbmkandf","hke","bmfcionm","inhcnlkbkkmjicn","jckjedhgoghi","chmik","mnjldknhaec","hocbccbg","ljadj","lciikgnlj","ifjjhkbhifione","foikblanoco","ode","mjc","fhklofh","mmoklkkog","hocbojmhffeajo","ccmmd","bkkh","nhhgcflniebkme","lfohikenfbjacli","cmehijnihijgng","caa","bmk","emofof","jjagiogohfab","ibh","eoacdlnodalkjbl","cbbjbbnjom","iljiomeloehen","gignlngclmh","b","ll","dokgngnocde","cienegffibgieba","agbachloidg","mlelnafokd","nmcmka","akeogjbjcnf","nebdic","a","efc","ljdk","jhcag","bkbikbjgae","mcjlgjeo","lo","dbiofobl","cehebiljff","eeagngm","ondahcjiel","coblanndhlhoggj","jaobmjml","jfejjinofek","hhnna","gdhcn","acelcomgkgohm","njkkjkkln","jmc","hkekoho","boefec","cioibfgjmhb","ebggdbeimn","emhg","cfghkhii","d","k","khoddedia","nhje","eebkfml","bohhd","kg","n","ilgemokdehcbaif","cldicda","einij","akmabcgfn","fmkmcn","bnlfbagkke","oakbgjejmcncj","iehdfadgoik","kkcfo","jmjkmfcacjjnd","ndokhh","hjfeelhckkjjmj","dnomohejbodkb","jcmblncjadno","oiofcodobiml","ddmillkncjfdfj","aihenmkdnhdhkhf","bfdbakeilfdojnc","jjhbkbne","aigabk","cae","oednojjb","gdoe","jokjceohkmbm","offkanbahigo","kfomigbfddjli","dkkjobgkcejei","mdilld","bofkika","kkinig","cljcflbghjmhmke","kmbjlgdcbdjn","bkgbmoahda","kmnajjdemggnfg","mgjndldil","iemb","kehaokgjg","icign","oijmaolehmoo","amhgldifmgekhe","diacnollhi","lnjhdaafadl","bdfiackhogoje","ebjlfa","deabkgfhnead","gadcob","haa","dbhnbhjcmmab","bbmjainilbbej","dc","bgjgafnjjflne","ehholgnn","fmhccbnc","mdnfl","feeejdgc","mfhlobdadooh","ojna","gkgjnijdbgo","ghngnhn","nhnjaiaadiedgg","nk","hmd","nmbmijaffogl","onkcgbgmago","gfli","ofjlec","nlfnbkkdc","hakilani","bofjdjkhllb","ocjncleljnecfc","gdonnkodmkejhf","fiflchanfllgnf","kaejakoibgln","hmdlfioacgaci","honmfbcog","mlacbi","gf","ejbbemoeha","acfjegee","lllflaocnnkeadi","mdgoebfgacecmbg","faejgln","kmlmhffgcmekm","akcjmgdg","kmhhh","fdohjehacdln","e","ojba","ohadmod","eaenkdiaokl","dii","cgfjaklblafeifo","imoeflkcgbbem","nbjkmb","jjgm","hofgelg","cnihecmdigdgflg","fnmikkeldjgb","onlhgonldjaedh","fmkdn","kfbcbleen","oejioibnmab","cg","meadghbocjnj","hmmdnkegfeieijn","ijgenomhndlje","maccdcgfjig","iabemie","mlfg","mdblmdaechmeaml","dhlafgjo","eabbiila","kf","oehggehfmijlfmg","klljaejidfhbon","akmbgmignoag","jgbkngmigdfm","kjeelnbn","ajaa","mlcjoiaahoiga","oalnielba","ffmobgkc","kmhoknfffdmo","nagjiffnjhh","dlehllomjok","agaejefhdbk","nnegoijfdj","ndl","dhfginocgi","nflmglgh","bcd","gbgjijemmdio","jk","gidgjbmb","hi","lmgoah","fdebefcech","ach","bahaoj","ccmmblgibgjahi","moid","jhilgedidndm","ldiakemnj","bbnibccm","jkbneoaheaajnm","clkgmbjlgdnl","lobbdldifnnijh","dnmih","jglia","didicmghfe","dlhbcfclf","akbmioocoihkfh","foofdldm","imenimfcame","ifekbmgnbdkc","jjlkaabdollola","gie","hbaj","noomfnfccmgaa","dcjffeg","nb","obdb","lolgjflimkib","eaiigminlakkb","cia","hkf","jknfklaio","igklbiomo","jfjgh","ekgnkfnhjcch","kmonfcclieehlik","oggkmccklnmj","bedhobcl","egmnhajcnhcdgb","imfdhekamfel","bmmkhfdbm","gnjfbcjlecfn","llmkgclm","gafinbnhfe","mlbfedkoeeddfao","kklcdmglleb","ckekmeiea","mi","kfejn","lm","mk","abkoajocfdili","jidac","jonhkanccl","lllodjgnmm","abfeaodlmjkngol","cdncnh","lkcb","abhilnmmhijab","hiljkfakojjld","mbboobkaolkljo","jhkblobaofgoh","ncm","mgbdhmcicomf","oag","akmjdd","abkenodnhj","mljf","afb","afejkobmiffeee","oollnkilabmb","gfaocokmcmjlmb","cokmecdd","bo","endocdnmjiek","bcf","lhllbagiel","bhihgofhj","ffce","neio","ofbfiiab","kjdo","lgfggnamceeo","kofledoinamcj","femhndomndoakoa","fmodaigcka","omakggcalhn","hhhogmcbjnhelkk","mgah","jghjjfmk","ecolelfmcb","eajjkdncafhhgab","obno","fifigfeok","laafjimienff","beckbbmhmofb","nafhihmgnikd","cbcfnhlkne","kao","nlkfhbm","fmh","ohfek","oj","hifgcgi","adhkn","lffgmodeafnn","ngchmhdbmhmhh","mcffimhnlffab","blhmkdhbnhbb","kkb","lgkine","hgfbdbfffanhik","joebhbh","img","kglcddmloo","hoflgfao","bdhgdekb","mggflahnoo","cmnol","imnmmgimmedf","mcjmoofomiia","mlakhbjfnbmgena","ilhmcnkkeg","domhbmkcd","fco","fdio","cmkoagblnd","kmihfigmceiiicm","afgbadbgbaon","menahlemehifooe","jacokdiiokaic","limj","fkedaoomokjbkdi","jkncd","jblmcmfnegnk","jjicjhjhbg","gbfcead","jf","aifnkmnao","effmhlhchngknl","odhjeib","ohcgmgb","bgbd","am","kkjfbdlh","hgbjakkokjgooel","jbeokiakf","flaoba","cifcdnanmk","mice","ihhofdai","ldnfmeiemhf","kefbbohhgineacj","bi","njfie","ociodahlomoekkf","andhoindeca","ajnndjocjeg","bmijkmjbbkgbbh","feanh","bjemcefkfcaenal","edfdenghinm","moal","ndbjdmijh","enccnhmoifa","dbckadjibam","gd","oglj","aldjelhbemle","cmbkofkcoe","ihciacibeh","lcojkclhmibgoif","jfmjncnolfj","gfcmcabhjki","aggfmakaanjb","mhbelld","hon","nkfoikcddehcah","kggbigknacmohb","jbkgndofcmaaohh","gkjano","afhhhh","mjng","jilckm","dekkedjehmenbm","clfm","acmhbkdadgena","oenokachg","lhiea","dceiag","eebgj","oolifidh","dj","cdfn","eghdglgiok","jdhegkefhbdhkm","mhgngafea","akabbcjkdnbc","gcbn","kimdgahf","oc"};
    	String[] ar = {"a","aa","aaaa"};
    	HashSet<String> d = new HashSet<String>();
    	for(String s:ar){
    		d.add(s);
    	}
    	
    	
    	String test = "test";
    	for(int i=0;i<=test.length();++i){
    		System.out.println(""+test.substring(0,i));
    	}
    	
    	String s;
    	
    	//we shall always remember this shit case, 
    	//like in a breadth first search, if the level of the final result is really deep/high, bfs is not a good choice/ easy to code though.
    	// and dfs can't find it too, cause there's no result, but we kept trying.
    	// here this simple case cause more than 70million function call but still no result, time limit will reach. 
    	s = "bccdbacdbdacddabbaaaadababadad";
    	s = "cbcdd";
    	s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
    	s = "aaaaaaa";
//    	s = "fajbeokiakfmlacbinjdnjdmmfha";
    	s = "a";
    	d.clear();
    	d.add(s);
    	ArrayList<String> res = wordBreak(s,d);
    	System.out.println();
    	System.out.println("res : "+res);
    	if(res!=null)
    	for(int i=0;i<res.size();++i){
    		System.out.println("  i: "+i+"    "+res.get(i));
    	}
    	
    }
    int shit =0;
    int good = 0;
    //I , find one, 
    //II , find all.
    private void checkMap(){
    	System.out.println(" check map, mapsize: "+resMap.size());
    	String s;
    	ArrayList<String> al;
    	int index =0;
    	Set<Entry<String, ArrayList<String>>> keyset = resMap.entrySet();
    	Iterator it = keyset.iterator();
    	while(it.hasNext()){
    		Entry<String,ArrayList<String>> e = (Entry<String, ArrayList<String>>) it.next();
    		s = e.getKey();
    		al = e.getValue();
    		System.out.println(""+index+++" string : "+s);
    		for(int i=0;i<al.size();++i){
    			System.out.print(" => "+al.get(i));
    		}
    		System.out.println("");
    	}
    	
    	for(int i=0;i<resMap.size();++i){
    		
    		
    	}
    	
    }
    
    
    
    
    //memory consuming but really fast, we save the whole tree.
    
    //change saved node from string to Arraylist, cause in this quest, we need to return all the cases.
    
    //too memory consuming to record final result in the hashmap, we only record links.
    // arraylist save success splits,  for example,  key:foobar , value: {foo,bar}.
    HashMap<String,ArrayList<String>> resMap = new HashMap<String,ArrayList<String>>();
    HashSet<String> deadSet = new HashSet<String>();
    int limit;
    public ArrayList<String> wordBreak(String s, Set<String> dict) {
    	
    	HashSet<String> dictSet = new HashSet<String>();
    	//init input 
    	limit = 0;
    	int startLen =Integer.MAX_VALUE;
    	String tmp;
        for(String d:dict){
//        	ArrayList<String> al = new ArrayList<String>();
//        	al.add(d);
        	//add an empty object, so we know it's a can, and it's no more split.
        	if(d.length()>limit){
        		limit = d.length();
        	}

            //!!!!!!!!!!!!!!!!dont' use else if cause maybe there's only one word in the dictionary!
        	if(d.length()<startLen){
        		startLen = d.length();
        	}
        	dictSet.add(d);
        }
        // we got a copy of dictionary, and word length limit in the dictionary, why we do that?
        // cause we shall also check if we have missed condition for these words in the dictionary
        // for examp, words in the dictionary are {"a","aa","aaaa"}
        // for now, in the resMap, is "aaaa" : solution count ,only 1, : "aaaa" =>"aaaa" ; we shall add "aaaa"=>{"a","a","a","a"},{"aa","a","a"}},{"aa","aa"},etc..
        
        
        ArrayList<String> toRemove = new ArrayList<String>();
        for(String word:dictSet){
        	if(word.length()==startLen){
//        		dictSet.remove(word);// can't remove any from the dictset as we are still in the loop of reversing the set.
        		ArrayList<String> al = new ArrayList();
        		al.add(word);
        		//init resmap from beginning.
        		resMap.put(word,al);
        	}
        }
        for(int i=0;i<toRemove.size();++i){
        	dictSet.remove(toRemove.get(i));
        }
        
        ArrayList<String> solutionHead;
        	int i = startLen;
        	while(dictSet.size()>0&&i<limit){
        		i++;
        	
        	// from short word to long ones, visit dictionary set many times. 
        	// for the real word, word length in the dictionary shall be not many, so the visit time will not exceed like 20*dictionarysize; 
        	// hope this tranverse will not be time consuming.
        	toRemove = new ArrayList<String>();
        	for(String word:dictSet){
        		if(word.length()==i){
        			//init resMap with these words in the dictionary first.
        			wordBreakAux(word,dict);
//        			dictSet.remove(word); // can't remove from set as we are visiting the set itself.
        			toRemove.add(word);
        			
        			solutionHead = resMap.get(word);
        			if(solutionHead==null){
        				solutionHead = new ArrayList<String>();
        				resMap.put(word, solutionHead);
        			}
        			solutionHead.add(word); //add itself as a solution.
        		}
        	}
        	
        	for(int r=0;r<toRemove.size();++r){
            	dictSet.remove(toRemove.get(r));
            }
        }
        
        System.out.println(" check map after we got dictionary.    before we use this for a real word");
        checkMap();
        
        // do recursive wordbreak process.
        // all the condition is saved in map, each word can be considered as a node in the map.
        wordBreakAux(s,dict);
        
        //get result in format requested.   link all the node saved in the map together.
        checkMap();
        
        ArrayList<String> res = getResultFormat(getWordBreakerList(s));
        
        
        return res;
    }
    
    
    
    
//    int shit =0;
    
    /**
     * no return during the process, save all the result in the map, so that will be
     * wordbreak function main entry, will be recursively called many times. 
     * named wordbreakAuxiliary cause main entry already named by leetcode framework, and we have to do init there, can't be called recursivly. 
     * @param s
     */
    public void wordBreakAux(String s,Set<String> dict) {
    	System.out.println("wordBreakSaved ,   : "+s);
    	if(resMap.containsKey(s)){
    		System.out.println("[good]"+good+++"    res ocunt: "+resMap.get(s).size());
    		return;
    	}
    	if(deadSet.contains(s)){
    		System.out.println("[shit]"+shit++);
    		return;
    	}
        int l = s.length();
        String head;
        String tail;
        boolean part;
        ArrayList<String> res = null;
        
        // the break process don't get a break of two combined word,   foobarplayer can only be {foo,barplayer} not { foobar,player}, cause foobar=>foo,bar, is complex.
        // the head part MUST be a word in the dictionary, the tail part can be a combined complex word.
        for(int i=1;i<l&&i<=limit;++i){
        	//i <l , so we will not get the word it self.  cause if the word itself is in the dictionary, it's already in the map,
        	head = s.substring(0,i);
        	tail = s.substring(i);
        	
        	if(!dict.contains(head)){
        		//head NOT in the dict, can't be a good split,
        		continue;
        	}
        	//prepare content.
        	wordBreakAux(tail,dict);
        	ArrayList al = null;
        	if(resMap.containsKey(tail)){
        		al = resMap.get(tail);
        	}
        	
        	if(al!=null){
        		//if al.size =0, the tail is also a word in the dictionary
        		
        		//find a path for this one, add to the result map;  after the for loop, all the possibilities are added, 
        		// if the array is  still null, this word will set to DEAD.
        		if(res==null){
        			res =new ArrayList<String>();
            		resMap.put(s, res);
        		}
        		// just add head, we know tail= string remove head, don't need to save it.
        		
//        		System.out.println(" s : "+s+"   split and konw this tail works: "+head+"-"+tail);
            	res.add(head);
//        		res.add(tail);
        	}
        }
        if(!resMap.containsKey(s)||resMap.get(s)==null){
        	System.out.println("DEAD   : "+l+"    : "+s);
        	deadSet.add(s);
        }else{
        	System.out.println("solution count: "+resMap.get(s).size()+"    : "+s);
        }
    }
    
    /**
     * one string to all the possible paths that this string word can split.  
     * the result of the break don't mean the final word in the dict.
     * for example.               
     * 
     * s: foobarplayer2000
     * splits:  {{ foobar, player2000},{foo,barplayer2000}}, cause foo and foobar are in the dictionary.  nothing break like {foobarplayer,2000} cause foobarplayer is complex.
     * final word in the dict: {foo,bar,foobar,player,2000}
     * 
     * so if a word s : {headword1,tail1},{headword2,tail2}.....{{headwordn,tailn}
     * the result is headwordi add to each getWordBreakerList(taili)
     * 
     * @param s
     * @return null if no solution.
     */
    private ArrayList<ArrayList<String>> getWordBreakerList(String stringForFormat){
//    	System.out.println("====================================== for : "+stringForFormat);
    	ArrayList<String> splitSolution = resMap.get(stringForFormat);
    	ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
    	if(splitSolution==null||splitSolution.size()==0){
//    		System.out.println(" no solution");
    		return res;
    	}
    	String head;
    	String tail;
    	ArrayList<ArrayList<String>> tailRes = null;
    	ArrayList<String> onePath = null;
    	for(int i=0;i<splitSolution.size();i++){
    		head = splitSolution.get(i);
    		tail = stringForFormat.substring(head.length());
    		System.out.println(" s to :  "+stringForFormat+"   "+head+"-"+tail);
    		tailRes = getWordBreakerList(tail);
    		System.out.println("tail solution size: "+tailRes.size()+"   tail:"+tail);
    		if(tailRes!=null&&tailRes.size()>0){
    			//for each solution of tail, add head.
    			for(int j=0;j<tailRes.size();++j){
    				onePath = tailRes.get(j);
    				onePath.add(0,head);
    				
    				
    				System.out.println("one path  for str =>"+stringForFormat+"   using head: "+head);
    	    		for(int k=0;k<onePath.size();++k){
    	    			System.out.print("==>  "+onePath.get(k));
    	    		}
    	    		res.add(onePath);
    			}
    		}else{
    			//tail has no content.
    			// just add haeeed.
    			onePath = new ArrayList<String>();
    			onePath.add(head);
    			res.add(onePath);
//    			System.out.println("empty tail  : "+tail+"   just add head: "+head);
    			
    			System.out.println("one path  for str =>"+stringForFormat+"   using head: "+head);
        		for(int k=0;k<onePath.size();++k){
        			System.out.print("==>  "+onePath.get(k));
        		}
    		}
    		System.out.println("");
    	}
    	
    	System.out.println("final : "+stringForFormat+"  res count: "+res.size());
    	return res;
    }
    
    
    private ArrayList<String> getResultFormat(ArrayList<ArrayList<String>> al){
    	ArrayList<String> res = new ArrayList<String>();
    	if(al==null){
    		return res;
    	}
    	ArrayList<String> onepath;
    	StringBuffer sb = new StringBuffer();
    	for(int i=0;i<al.size();++i){
    		onepath = al.get(i);
    		sb = new StringBuffer();
    		for(int j=0;j<onepath.size();++j){
    			sb.append(onepath.get(j));
    			sb.append(" ");
    		}
    		sb.setLength(sb.length()-1); //remove last " "
    		res.add(sb.toString());
//    		System.out.print(" path "+i+" : "+sb.toString());
    	}
    	return res;
    }
    
    
//    private void addToResMap(String s,ArrayList<String> al1,ArrayList<String> al2){
//    	ArrayList<String> res = null; 
//    	if(resMap.containsKey(s)){
//    		res = resMap.get(s);
//    	}else{
//    		res =new ArrayList<String>();
//    		resMap.put(s, res);
//    	}
//    	
//    	String s1,s2;
//    	for(int i=0;i<al1.size();++i){
//    		for(int j=0;j<al2.size();++j){
//    			s1 = al1.get(i);
//    			s2 = al2.get(j);
//    			res.add(s1+" "+s2);
//    		}
//    	}
//    	resMap.put(s, res);
//    }
    
}