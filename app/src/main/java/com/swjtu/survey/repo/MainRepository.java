package com.swjtu.survey.repo;

import android.os.Environment;

import com.android.framekit.repo.IRepository;
import com.android.framekit.utils.EncryptUtil;
import com.android.framekit.utils.FileUtil;
import com.google.gson.Gson;
import com.swjtu.survey.Constants;
import com.swjtu.survey.bean.SurveyProjectBean;
import com.swjtu.survey.utils.LogUtil;
import com.swjtu.survey.utils.ToastUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.android.framekit.extensions._StringKt.toNormalTimeStr;

public class MainRepository implements IRepository {

    /**
     * 获取工程列表，工程是放在本地的文件夹，每个工程文件夹下对应配置文件，如果配置文件有误，将不会被解析加载
     * @return 格式正确的工程描述列表
     */
    public List<SurveyProjectBean> getHistoryProjects() {
        String localSDCardPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String rooPath = localSDCardPath + File.separator + Constants.SYSTEM_ROOT_NAME + File.separator + Constants.SYSTEM_PROJECT_ROOT_NAME;
        File projectDir = new File(rooPath);
        if (!projectDir.exists()) {
            return null;
        }
        ArrayList<String> projects = (ArrayList<String>) FileUtil.INSTANCE.getChildFileContent(rooPath, "config");
        if (projects == null || projects.size() == 0) {
            LogUtil.i(null, "null-------");
            return null;
        }
        ArrayList<SurveyProjectBean> surveys = new ArrayList<>();
        Gson gson = new Gson();
        for (int i = 0; i < projects.size(); i++) {
            LogUtil.i(null, "count-------" + projects.get(i));
            SurveyProjectBean surveyProjectBean = new SurveyProjectBean();
            surveyProjectBean = gson.fromJson(projects.get(i), SurveyProjectBean.class);
            if (surveyProjectBean != null
                    && surveyProjectBean.getId() != null
                    && surveyProjectBean.getId().equals(EncryptUtil.INSTANCE.encryptMD5ToString(surveyProjectBean.getProjectName()))) {
                surveys.add(surveyProjectBean);
            }
        }
        return surveys;
    }

    /**
     * 创建指定工程名的文件夹
     * type: file
     */
    public String createNewProject(String projectName) {
        if (projectName == null) {
            return null;
        }
        if (projectName.isEmpty()) {
            return null;
        }
        //创建根路径
        String localSDCardPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String rooPath = localSDCardPath + File.separator + Constants.SYSTEM_ROOT_NAME + File.separator + Constants.SYSTEM_PROJECT_ROOT_NAME;
        FileUtil.INSTANCE.createFileDir(rooPath, false);
        //创建工程路径
        String projectPath = rooPath + File.separator + projectName;
        if (new File(projectPath).exists()) {
            ToastUtils.showToast("该工程已经存在");
            return null;
        }
        if (FileUtil.INSTANCE.createFileDir(projectPath, true)) {
            return projectPath;
        }
        return null;
    }

    /**
     * 创建工程的摘要，该摘要和数字证书不同，保存工程的基本信息，可以用于对象数据库
     * type: file
     */
    public SurveyProjectBean createProjectSignature(String projectPath, String projectName) {
        if (projectPath == null) {
            return null;
        }
        if (projectPath.isEmpty()) {
            return null;
        }
        File projectFile = new File(projectPath);
        if (!projectFile.exists()) {
            return null;
        }
        String signaturePath = projectFile + File.separator + Constants.PROJECT_CONFIG_NAME;
        FileUtil.INSTANCE.createFile(signaturePath, true);

        //写入摘要
        SurveyProjectBean surveyProjectBean = new SurveyProjectBean();
        surveyProjectBean.setId(EncryptUtil.INSTANCE.encryptMD5ToString(projectName));
        surveyProjectBean.setCreateTime(toNormalTimeStr(System.currentTimeMillis()));
        surveyProjectBean.setProjectName(projectName);
        surveyProjectBean.setLocalPath(projectPath);

        FileUtil.INSTANCE.writeFile(signaturePath, false, new Gson().toJson(surveyProjectBean));
        return surveyProjectBean;
    }


}
