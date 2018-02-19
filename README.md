# Verkefnalýsing

Í [kafla 3](http://web.engr.illinois.edu/~jeffe/teaching/algorithms/notes/03-backtracking.pdf) í kennslubók námskeiðsins er á bls. 2-4 lýst leik sem við höfum nefnt Sykurmolaleikinn (e. Fake-sugar Packet Game). Í kaflanum er lýst útgáfu sem er spiluð á 3x3 grind, þar sem hvor leikmaður hefur 3 sykurmola. Þið eigið að skrifa forrit sem spilar þessa 3x3 útgáfu og reyna að hafa forritið ykkar þannig að það spili leikinn eins vel og hægt er.
Leikjatréð fyrir 3x3 útgáfuna er ekki gríðarlega stórt, hver hnútur hefur í mesta lagi 3 börn og hvor leikmaður getur í mesta lagi leikið 12 leiki (þá eru allir sykurmolarnir komnir yfir borðið), þannig að mesta möguleg hæð leikjatrésins er 23 (= 12 + 11, því við hættum þegar annar er búinn að vinna). Það er því (líklega!) fýsilegt að fara í gegnum allt leikjatréð og finna í hvert sinn besta leikinn.

Það eru til margar heimildir um hvernig best er að leita í leikjatré. Aðalaðferðin heitir Minimax leit og skiptist á að vera í hlutverki leikmannanna tveggja. Hjá okkur þarf ekki að meta stöðuna með gildi, heldur er staða annað hvort unnin (Good) eða töpuð (Bad). Þá má lýsa aðferðinni á einfaldari hátt:

      Play(X, player)
         if player has already won in state X
            return GOOD
         if player has already lost in state X
            return BAD
         for all legal moves X -> Y
            if Play(Y, -player) == BAD
          return GOOD
         return BAD

Síðan þarf líka að koma leikjunum til skila, en reyndar þarf aðeins einn leik, þann fyrsta sem leikmaðurinn á að gera.
Þið getið líka gert einfaldara forrit, sem reynir að beita "hyggjuviti" til að spila leikinn, þ.e. hefur nokkrar þumalfingursreglur og beitir þeim á stöðuna sem er uppi á hverjum tíma.

Forritið ykkar þarf ekki að hafa flott notendaviðmót. Það er nóg að það geti tekið við leik notandans og birt sinn eigin leik. Það þarf þó að spila leikinn rétt, þ.e. gera aðeins löglega leiki og geta sagt hvenær leikur er unninn/tapaður. Skrifið stutta skýrslu um forriti og sýnið keyrslu þess. Látið viðeigandi kóða fylgja með.