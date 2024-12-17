package com.app.raghu.controller.files;

import com.app.raghu.entity.files.Cases;
import com.app.raghu.service.files.CaseFilesService;
import com.app.raghu.service.files.CasesService;
import com.app.raghu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@RequestMapping("/files")
public class CasesController {

    @Autowired
    private CasesService casesService;

    @Autowired
    private CaseFilesService caseFilesService;

    // 查询案件（支持分页和条件查询）
    @GetMapping("/like_case")
    public Result searchCases(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String caseNumber,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Cases> casesPage = casesService.searchCases(title, caseNumber, status, pageable);
        return Result.success(casesPage);
    }

    // 添加案件
    @PostMapping("/add_case")
    public Result addCase(@RequestBody Cases cases) {
        if (casesService.existsByCaseNumber(cases.getCaseNumber())) {
            return new Result(500, "error", "案件编号重复: " + cases.getCaseNumber());
        }
        cases.setCreatedAt(LocalDateTime.now());
        cases.setUpdatedAt(LocalDateTime.now());

        // 保存案件数据
        casesService.saveCases(cases);
        return new Result(200, "success", "添加成功");
    }

    // 更新案件
    @PutMapping("/updated_case")
    public Result updateCase(@RequestBody Cases cases) {
        // 检查案件ID是否存在
        if (!casesService.existsById(cases.getId())) {
            return Result.error("案件ID不存在");
        }
        ArrayList<Cases> caseList = casesService.updateCase(cases);
        if (caseList == null || caseList.size() == 0) {
            return Result.error("案件编号已存在");
        }
        // 更新案件
        return Result.success(caseList);
    }

    // 删除案件
    @DeleteMapping("/delete_case/{id}")
    public Result deleteCase(@PathVariable Integer id) {
        // 检查案件是否已绑定文件
        if (caseFilesService.getExistsByCaseId(id)) {
            return new Result(500, "error", "删除失败，案件已绑定文件");
        }

        // 删除案件
        if (casesService.deleteCaseByCaseNumber(id)) {
            return new Result(200, "success", "删除成功");
        } else {
            return new Result(500, "error", "案件编号不存在");
        }
    }

}
