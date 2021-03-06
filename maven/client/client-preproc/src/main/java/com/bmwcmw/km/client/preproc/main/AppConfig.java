package com.bmwcmw.km.client.preproc.main;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.bmwcmw.km.common.io.IOUtils;

/**
 * Initial configuration settings
 * @author CMWT420
 *
 */
public class AppConfig {
	private static AppConfig config = null;
	
	// XXX SETUP : Base directory
	public final static String _workingDir = System.getProperty("user.dir");
	public Map<String, String> _ctlParams;

	public int _nbThreads;
	public int _compressMode;
	public boolean _writeprecompare;
	public int _blockLineNb;
	public int _blockLineLength;
	public int _blockTotalTerms;
	public int _precompareMode;
	public int _compareMode;
	public int _distributeMode;
	public int _indicatorMode;
	
    private AppConfig() { 

		// XXX SETUP : Logs on/off
		IOUtils.setLogging(true, false);
		
		// XXX SETUP : Compressor mode, perhaps needs external DB support
		_compressMode = AppConstants.APPCOMPRESS_INRAM;
		// XXX SETUP : Compressor writes sorted S/O files of each predicate or 
		// 			   not (InRam only)
		_writeprecompare = true;
		// XXX SETUP : Set the first to 0 if we want to disable the block mode. Then the second one 
		//			   will be automatically disabled.
		_blockLineNb = 10000;
		_blockLineLength = 32768;
		_blockTotalTerms = 1024768;
		
//		// XXX SETUP : Pre-Comparator mode, perhaps needs PERL executable in PATH
//		_precompareMode = AppConstants.PRECOMPARE_JAVA;
		
		// XXX SETUP : Comparator mode, perhaps needs PERL/GNU executable in PATH
		_compareMode = AppConstants.APPCOMPARE_JAVA;
		
		// XXX SETUP : Distributor mode, to various distributed environments
		_distributeMode = AppConstants.APPDISTRIBUTE_HDFS;
		
		// XXX SETUP : Use only S/O or S and O for indicator in the distribution
		_indicatorMode = AppConstants.APPINDICATORSO;
		
		// XXX SETUP : Global in/out paths
		_ctlParams = new HashMap<String, String>();
		_ctlParams.put("rdfPath", _workingDir + File.separator + ".." 
				+ File.separator + "DataSet" + File.separator + "__rdf");
		_ctlParams.put("n3Path", _workingDir + File.separator + ".." 
				+ File.separator + "DataSet" + File.separator + "__n3(26M)");
		_ctlParams.put("invalidPath", _workingDir + File.separator + "_invalidTriple");
		_ctlParams.put("psPath", _workingDir + File.separator + "_ps");
		_ctlParams.put("posPath", _workingDir + File.separator + "_pos");
		_ctlParams.put("nsPath", _workingDir + File.separator + "_ns");
		_ctlParams.put("compressedPath", _workingDir + File.separator + "_compressed");
		_ctlParams.put("comparePath", _workingDir + File.separator + "_compare");
		_ctlParams.put("indicatorPath", _workingDir + File.separator + "_indicator");

		for (String key : _ctlParams.keySet())
			IOUtils.logLog("Using parameter : " + key + " - " + _ctlParams.get(key));
    }
    
    public static synchronized AppConfig getInstance() {
        if (config == null) {
        	config = new AppConfig();
        }
        return config;
    }
}
