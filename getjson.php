<?php 

    error_reporting(E_ALL); 
    ini_set('display_errors',1); 

    include('dbcon.php');
        

    $stmt = $con->prepare('select * from metro');
    $stmt->execute();

    if ($stmt->rowCount() > 0)
    {
        $data = array(); 

        while($row=$stmt->fetch(PDO::FETCH_ASSOC))
        {
            extract($row);
    
            array_push($data, 
                array('line'=>$line,
                'cur_station'=>$cur_station,
                'rem_next_time'=>$rem_next_time
            ));
        }

        header('Content-Type: application/json; charset=utf8');
        $json = json_encode(array("열차 데이터"=>$data), JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
        echo $json;
    }

?>