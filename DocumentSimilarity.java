public class DocumentSimilarity
{
   public static void main(String[] args)
   {
      //document 1
   	Document a = new Document ("The game of life is a game of everlasting learning");
      setUpDocument(a);
      //document 2
      Document b = new Document ("I have received the greatest gift of life");
      setUpDocument(b);
      //document 3
      Document c = new Document ("Never stop learning");
      setUpDocument(c);
      //document 4
      Document e = new Document ("Don't stop learning");
      setUpDocument(e);
      
      //document array
      
      Document [] array = {a, b, c, e};
      
      a.calculateIdfs(array);
      b.calculateIdfs(array);
      c.calculateIdfs(array);
      e.calculateIdfs(array);
      
         
      //calculates first doc to 2 and 3
      calculateSimilarity(a, b);
      calculateSimilarity(a, c);   
      
      //calculates 2nd doc to 1 and 3
      calculateSimilarity(b, a);
      calculateSimilarity(b, c);
      
      //calculates 3rd doc to 2 and 4
      calculateSimilarity(c, b);
      calculateSimilarity(c, e);
      
      
      
    
     
  
         
   }
   
	
   public static void setUpDocument(Document d)
   {
      d.findUniqueTerms();
      d.calculateTermFrequency();
      d.calculateNormalizedTermFrequency();
      

         
   }
 
   

   

	
   public static void calculateSimilarity(Document d1, Document d2)
   {
      double[] iXt1 = calculateFreqXIdf(d1);
      double[] iXt2 = calculateRelFreqXIdf(d2, d1.getUniqueTerms());
   	
      double numer = 0.0, doc1sq = 0.0, doc2sq = 0.0;
      for (int i = 0; i < iXt1.length; i++)
      {
         numer += iXt1[i] * iXt2[i];
         doc1sq += Math.pow(iXt1[i], 2);
         doc2sq += Math.pow(iXt2[i], 2);
      }
   	
      double similarity = 0.0;
      if (doc1sq > 0 && doc2sq > 0)
         similarity = numer / (Math.sqrt(doc1sq) * Math.sqrt(doc2sq));
   	
      System.out.println(similarity);
   }
	
   public static double[] calculateFreqXIdf(Document d)
   {
      String[] terms = d.getUniqueTerms();
      double[] idfs = d.getIdfs();
      double[] ntfs = d.getNormalizedTermFreq();
      double[] iXt = new double[terms.length];
      for (int i = 0; i < iXt.length; i++)
      {
         iXt[i] = idfs[i] * ntfs[i];
      }
      return iXt;
   }
	
   public static double[] calculateRelFreqXIdf(Document d, String[] terms)
   {
      String[] terms2 = d.getUniqueTerms();
      double[] idfs = d.getIdfs();
      double[] ntfs = d.getNormalizedTermFreq();
      double[] iXt = new double[terms.length];
   	
      for (int i = 0; i < terms.length; i++)
      {
         for (int j = 0; j < terms2.length; j++)
         {
            if (terms[i].equals(terms2[j]))
            {
               iXt[i] = idfs[j] * ntfs[j];
            }
         }
      }
      return iXt;
 
}

}
